package co.edu.icesi.taller1.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import co.edu.icesi.taller3.model.TsscGame;
import co.edu.icesi.taller3.model.TsscStory;
import co.edu.icesi.taller3.model.TsscTimecontrol;
import co.edu.icesi.taller3.model.TsscTopic;
import co.edu.icesi.taller3.repository.GameRepository;
import co.edu.icesi.taller3.repository.TopicRepository;
import co.edu.icesi.taller3.service.GameServiceImp;

@RunWith(MockitoJUnitRunner.class)
class GameTest {

	@Mock
	public GameRepository gameDao;
	
	@Mock
	public TopicRepository topicDao;
	
	@InjectMocks
	public GameServiceImp gameService;
	
	public TsscGame game;
	
	@BeforeEach
	public void setUp1() {
		MockitoAnnotations.initMocks(this);
		game = new TsscGame();
	
		
		
	}
	@Nested
	class AddGame {
	
		@DisplayName("Agregar juego null")
		@Test 
		public void addNullGameTestThrowsException() {
			
			game.setNGroups(1);
			game.setNSprints(5);
			assertThrows(Exception.class,() -> {
	    		gameService.saveGame(null,null);
	    	});
		
			verifyZeroInteractions(gameDao);
			
			
			
			
		}
	@DisplayName("Agregar juego Sprints <=0")
	@Test 
	public void gameAddTestSprintsThrowsException() {
		
		game.setNSprints(1);
		game.setNGroups(0);
		
		
		
		assertThrows(Exception.class,() -> {
    		gameService.saveGame(game,null);
    	});
		verifyZeroInteractions(gameDao);
		
	}
	
	@DisplayName("Agregar juego Sprints y Groups >0")
	@Test 
	public void gameAddTestSprintsAndGroupsNoThrowsException() {
		
		game.setNSprints(1);
		game.setNGroups(4);
		
		when(gameDao.save(game)).thenReturn(game);
		try {
			assertEquals(gameService.saveGame(game,null),game);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
		verify(gameDao,times(1)).save(game);
		
		
		
	}
	@DisplayName("Agregar juego Groups <=0")
	@Test 
	public void gameAddTestGroupsThrowsException() {
		
		game.setNGroups(-2);
		game.setNSprints(1);
		
		
		
		assertThrows(Exception.class,() -> {
    		gameService.saveGame(game,null);
    		
    	});
		
		verifyZeroInteractions(gameDao);
		
		
		
	}
	
	//Verificar que si se asocia un tema y no existe, lance una excepcion
	@DisplayName("Agregar juego con tema inexistente")
	@Test 
	public void gameAddTestTopicNullThrowsException() {
		TsscTopic topic = new TsscTopic();
		
		when(topicDao.findById(topic.getId())).thenReturn(null);
		
		assertThrows(NullPointerException.class,() -> {
    		gameService.saveGame(game,topic);
    		
    	});
		
		verifyZeroInteractions(gameDao);

	}
	
	
	
	
	
	//Verificar que si se asocia un tema, exista
	@DisplayName("Agregar juego con tema existente")
	@Test 
	public void gameAddTestTopicNoNull() {
		TsscTopic topic = new TsscTopic();
		
		
		when(topicDao.findById(topic.getId())).thenReturn(Optional.of(topic));
		//when(gameRepo.save(game)).thenReturn(game);
		try {
			assertEquals(gameService.saveGame(game,topic),game);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
		verify(gameDao,times(1)).save(game);
		
		
		
		
	}
	
	
	
	}
	
	@Nested
	class AddGame2 {
		
		public TsscTopic topic;
		
		public List<TsscStory> stories;
		public TsscStory story2;
		public List<TsscTimecontrol> times;
		public TsscTimecontrol time2;
		@BeforeEach
		public void setUp2() {
			story2 = new TsscStory();
			topic = new TsscTopic();
			stories = new ArrayList<TsscStory>();
			topic.setTsscStories(stories);
			topic.addTsscStory(story2);
			
			times = new ArrayList<TsscTimecontrol>();
			time2 = new TsscTimecontrol();
			
			topic.setTsscTimeControls(times);
			topic.addTsscTimeControl(time2);
			game.setNSprints(1);
			game.setNGroups(4);
		
		}
		@DisplayName("Agregar juego con historias del tema")
		@Test 
		public void gameAddTestStories() {
			
			when(topicDao.findById(topic.getId())).thenReturn(Optional.of(topic));
			
			try {
				assertEquals(gameService.saveGame2(game,topic),game);
				assertNotNull(game.getTsscStories());
				assertEquals(game.getTsscStories(),topic.getTsscStories());
				assertTrue(game.getTsscStories().size()==topic.getTsscStories().size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
			verify(gameDao,times(1)).save(game);
			
			
			
			
		}
		
		@DisplayName("Agregar juego con cronogramas del tema")
		@Test 
		public void gameAddTestTimeControls() {
			
			
			when(topicDao.findById(topic.getId())).thenReturn(Optional.of(topic));
			
			try {
				
				assertEquals(gameService.saveGame2(game,topic),game);
				assertNotNull(game.getTsscTimecontrols());
				assertEquals(game.getTsscTimecontrols(),topic.getTsscTimeControls());
				assertTrue(game.getTsscTimecontrols().size()==topic.getTsscTimeControls().size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
			verify(gameDao,times(1)).save(game);
			
			
			
			
		}
		
		@DisplayName("Agregar juego con las historias del tema null. Lanza excepcion")
		@Test 
		public void gameAddTestWithoutStoriesThrowException() {
			TsscTopic topic2 = new TsscTopic();
			
			when(topicDao.findById(topic2.getId())).thenReturn(Optional.of(topic2));
			
			assertThrows(Exception.class,() -> {
	    		gameService.saveGame2(game,topic2);
	    		
	    	});
			
			assertNull(game.getTsscStories());
			verify(gameDao,times(0)).save(game);
		
		}
		
		@DisplayName("Agregar juego con los cronogramas del tema null. Lanza excepcion")
		@Test 
		public void gameAddTestWithoutTimesThrowException() {
			TsscTopic topic2 = new TsscTopic();
			
			when(topicDao.findById(topic2.getId())).thenReturn(Optional.of(topic2));
			
			assertThrows(Exception.class,() -> {
	    		gameService.saveGame2(game,topic2);
	    		
	    	});
			
			
			assertNull(game.getTsscTimecontrols());
			verify(gameDao,times(0)).save(game);
		}
		
		
		
	}


	@Nested
	class EditGame {
		
		@BeforeEach
		public void setUp2() {

			//when(gameRepo.save(game)).thenReturn(game);
			Optional<TsscGame> game1 = Optional.of(game);
			when(gameDao.findById(game.getId())).thenReturn(game1);
			
		
		}
		
		@DisplayName("Editar juego que no existe")
		@Test 
		public void editNullGameTestThrowsException() {
			
			game.setNGroups(1);
			game.setNSprints(5);
			assertThrows(Exception.class,() -> {
	    		gameService.editGame(null,null);
	    	});
		
			verify(gameDao,times(0)).save(game);
			
			
			
			
		}
		//Sprints And Groups >0
		@DisplayName("Editar con Sprints y Groups >0")
		@Test 
		public void gameEditTestNoThrowsException() {
			
			game.setNGroups(1);
			game.setNSprints(2);

	
			try {
				assertEquals(gameService.editGame(game,null),game);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
		
			
	
		}
		//Sprints And Groups <=0
		@DisplayName("Editar con Sprints y Groups <=0")
		@Test 
		public void gameEditTestThrowsException() {
			
			game.setNGroups(1);
			game.setNSprints(-5);
			assertThrows(Exception.class,() -> {
	    		gameService.editGame(game,null);
	    	});
		
			verifyZeroInteractions(topicDao);
			
			
			
			
		}
		
		@DisplayName("Editar juego con tema inexistente")
		@Test 
		public void gameEditTestTopicNullThrowsException() {
			TsscTopic topic = new TsscTopic();
			
			when(topicDao.findById(topic.getId())).thenReturn(null);
			
			assertThrows(Exception.class,() -> {
	    		gameService.editGame(game,topic);
	    		
	    	});
			
			verify(gameDao,times(0)).save(game);

		}
		
		//Verificar que si se asocia un tema, exista
		@DisplayName("Editar juego con tema existente")
		@Test 
		public void gameEditTestTopicNoNull() {
			TsscTopic topic = new TsscTopic();

			
			when(topicDao.findById(topic.getId())).thenReturn(Optional.of(topic));
			try {
				assertEquals(gameService.editGame(game,topic),game);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
			verify(gameDao,times(1)).findById(game.getId());
			verify(gameDao,times(1)).save(game);
			
			
			
		}
	}
	


}
