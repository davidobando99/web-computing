package co.edu.icesi.taller1.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.taller1.model.TsscGame;
import co.edu.icesi.taller1.model.TsscStory;
import co.edu.icesi.taller1.model.TsscTimecontrol;
import co.edu.icesi.taller1.model.TsscTopic;
import co.edu.icesi.taller1.repository.GameRepository;
import co.edu.icesi.taller1.repository.TopicRepository;
import co.edu.icesi.taller1.service.GameServiceImp;
import co.edu.icesi.taller1.service.TopicServiceImp;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameTest {

	
	
	@Autowired
	public GameServiceImp gameService;
	
	@Autowired
	public TopicServiceImp topicService;
	
	public TsscGame game;
	public TsscTopic topic;
	
	@BeforeEach
	public void setUp1() {
	
		game = new TsscGame();
		topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		try {
			topicService.saveTopic(topic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@DisplayName("Agregar Juego sin Tema asociado")
	@Test 
	public void testSaveGameNotTopic() {
		
		game.setNSprints(1);
		game.setNGroups(4);
		

		try {
			assertEquals(gameService.saveGame(game,null),game);
			assertTrue(gameService.saveGame(game, null).getNGroups()>0);
			assertNull(gameService.saveGame(game, null).getTsscTopic());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}

	}
		
		@DisplayName("Agregar juego con tema asociado")
		@Test 
		public void testSaveGameWithTopic() {
			
			game.setNSprints(1);
			game.setNGroups(4);
			
			TsscGame game2 = null;
			try {
				assertEquals(gameService.saveGame(game,topic),game);
				assertTrue(gameService.saveGame(game, topic).getNGroups()>0);
				assertEquals(gameService.saveGame(game, topic).getTsscTopic(),topic);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
		}
		
		//Punto D
		@DisplayName("Agregar juego asociado con cronogramas e historias del tema")
		@Test 
		public void testSaveGame2WithTopic() {
			

			game.setNSprints(1);
			game.setNGroups(4);
			
			List<TsscTimecontrol> times = new ArrayList<TsscTimecontrol>();
			List<TsscStory> stories = new ArrayList<TsscStory>();
			TsscStory story = new TsscStory();
			TsscTimecontrol control = new TsscTimecontrol();
			control.setName("stop");
			topic.setTsscStories(stories);
			topic.setTsscTimeControls(times);
			topic.addTsscStory(story);
			topic.addTsscTimeControl(control);
			
			TsscGame game1=null;
			try {
				game1 = gameService.saveGame2(game,topic);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				fail();
			}
		
			assertEquals(game1,game);
			assertTrue(game1.getNGroups()>0);
			assertEquals(game1.getTsscTopic(),topic);
			assertNotNull(game1.getTsscTimecontrols());
			assertNotNull(game1.getTsscStories());
			assertEquals(game1.getTsscTimecontrols().size(),topic.getTsscTimeControls().size());
			assertEquals(game1.getTsscTimecontrols().get(0).getName(),
					topic.getTsscTimeControls().get(0).getName());
			assertTrue(game1.getTsscTimecontrols().containsAll(topic.getTsscTimeControls()));
			assertTrue(game1.getTsscStories().containsAll(topic.getTsscStories()));
		
			
			
			
			

		
			
		}


		@DisplayName("Editar juego sin tema asociado")
		@Test 
		public void testEditGameNoTopic() {
			
			game.setNGroups(1);
			game.setNSprints(2);
			game.setName("game1");
			try {
				gameService.saveGame(game, null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	
			try {
				assertEquals(gameService.editGame(game,null),game);
				assertTrue(gameService.editGame(game, null).getName().equals("game1"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
			
			game.setName("game2");
			try {
				assertEquals(gameService.editGame(game,null),game);
				assertTrue(gameService.editGame(game, null).getName().equals("game2"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
			
	
		}
		
		@DisplayName("Editar juego con tema asociado")
		@Test 
		public void testEditGameWithTopic() {
			
			game.setNGroups(1);
			game.setNSprints(2);
			game.setName("game1");
			try {
				gameService.saveGame(game, topic);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	
			try {
				assertEquals(gameService.editGame(game,topic),game);
				assertTrue(gameService.editGame(game, topic).getName().equals("game1"));
				assertTrue(gameService.editGame(game, topic).getTsscTopic().equals(topic));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
			
			game.setName("game2");
			try {
				assertEquals(gameService.editGame(game,topic),game);
				assertTrue(gameService.editGame(game, topic).getName().equals("game2"));
				
				//Editar juego con otro tema
				
				TsscTopic topic2 =  new TsscTopic();
				topic2.setDefaultGroups(1);
				topic2.setDefaultSprints(1);
				try {
					topicService.saveTopic(topic2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				assertTrue(gameService.editGame(game, topic2).getTsscTopic().equals(topic2));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
			
	
		}
	

}
