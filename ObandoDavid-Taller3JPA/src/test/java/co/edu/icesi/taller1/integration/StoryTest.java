package co.edu.icesi.taller1.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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

import co.edu.icesi.taller3.Taller3JPA;
import co.edu.icesi.taller3.model.TsscGame;
import co.edu.icesi.taller3.model.TsscStory;
import co.edu.icesi.taller3.repository.GameRepository;
import co.edu.icesi.taller3.repository.StoryRepository;
import co.edu.icesi.taller3.service.GameServiceImp;
import co.edu.icesi.taller3.service.StoryServiceImp;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Taller3JPA.class)
class StoryTest {

	
		@Autowired
		public StoryServiceImp storyService;
		
		@Autowired
		public GameServiceImp gameService;
		
		public TsscStory story;
		
		public TsscGame game;
		
		public TsscGame game1;
		
		@BeforeEach
		public void setUp1() {
			
			story = new TsscStory();
			game = new TsscGame();
			game1 = new TsscGame();
			story.setBusinessValue(new BigDecimal(9));
			story.setInitialSprint(new BigDecimal(3));
			story.setPriority(new BigDecimal(2));
			try {
				gameService.saveGame(game, null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				gameService.saveGame(game1, null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
		@DisplayName("Agregar historia con juego asociado")
		@Test 
		public void testAddStory() {
		
			
			try {
				assertEquals(storyService.saveStory(story,game),story);
				assertEquals(storyService.saveStory(story, game).getTsscGame(),game);
		
				assertTrue(storyService.saveStory(story, game).getInitialSprint().
						compareTo(new BigDecimal(0))>0);
				assertTrue(storyService.sizeStories()==1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
			
			
			
		}
		
		@DisplayName("Editar historia con juego asociado")
		@Test 
		public void storyEditTestThrowsException() {
			
		try {
			storyService.saveStory(story, game);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			story.setDescription("hola");
			
	    	try {
				assertTrue(storyService.editStory(story,game).getDescription().equals("hola"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
	    	
	    	story.setDescription("hola2");
	    	
	    	try {
				assertTrue(storyService.editStory(story,game).getDescription().equals("hola2"));
				assertEquals(storyService.editStory(story, game1).getTsscGame(),game1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
	    
		
			
			
			
			
			
		}
		
		

	}



