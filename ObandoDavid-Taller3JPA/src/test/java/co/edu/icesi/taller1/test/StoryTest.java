package co.edu.icesi.taller1.test;

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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import co.edu.icesi.taller3.model.TsscGame;
import co.edu.icesi.taller3.model.TsscStory;
import co.edu.icesi.taller3.repository.GameRepository;
import co.edu.icesi.taller3.repository.StoryRepository;
import co.edu.icesi.taller3.service.StoryServiceImp;
@RunWith(MockitoJUnitRunner.class)
class StoryTest {

		@Mock
		public StoryRepository storyRepo;
		@Mock
		public GameRepository gameRepo;
		
		
		
		@InjectMocks
		public StoryServiceImp storyService;
		
		public TsscStory story;
		
		public TsscGame game;
		
		@BeforeEach
		public void setUp1() {
			MockitoAnnotations.initMocks(this);
			story = new TsscStory();
			game = new TsscGame();
		
			
			
		}
		@Nested
		class AddStory {
		
			@DisplayName("Agregar historia null")
			@Test 
			public void storyAddTestNull() {
				when(gameRepo.findById(game.getId())).thenReturn(Optional.of(game));
				story.setBusinessValue(new BigDecimal(9));
				story.setInitialSprint(new BigDecimal(3));
				story.setPriority(new BigDecimal(2));
				
				
				assertThrows(Exception.class,() -> {
		    		storyService.saveStory(null,game);
		    	});
				verifyZeroInteractions(storyRepo);
				
				
				
		}
		@DisplayName("Agregar historia, Business Value <=0")
		@Test 
		public void storyAddTestBusinessValueThrowsException() {
			
			when(gameRepo.findById(game.getId())).thenReturn(Optional.of(game));
			story.setBusinessValue(new BigDecimal(-1));
			story.setInitialSprint(new BigDecimal(3));
			story.setPriority(new BigDecimal(2));
			
			
			
			assertThrows(Exception.class,() -> {
	    		storyService.saveStory(story,game);
	    	});
			verifyZeroInteractions(storyRepo);
			
		}
		
		@DisplayName("Agregar historia, Business Value, Initial Sprint y Priority >0")
		@Test 
		public void storyAddTestSprintsNoThrowsException() {
			when(gameRepo.findById(game.getId())).thenReturn(Optional.of(game));
			story.setBusinessValue(new BigDecimal(9));
			story.setInitialSprint(new BigDecimal(3));
			story.setPriority(new BigDecimal(2));
			
			
			try {
				assertEquals(storyService.saveStory(story,game),story);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
			verify(storyRepo,times(1)).save(story);
			
			
			
		}
		@DisplayName("Agregar historia, Sprint inicial <=0")
		@Test 
		public void storyAddTestSprintThrowsException() {
			when(gameRepo.findById(game.getId())).thenReturn(Optional.of(game));
			story.setBusinessValue(new BigDecimal(9));
			story.setInitialSprint(new BigDecimal(0));
			story.setPriority(new BigDecimal(2));
			
			
			
			assertThrows(Exception.class,() -> {
	    		storyService.saveStory(story,game);
	    		
	    	});
			
			verifyZeroInteractions(storyRepo);
			
			
			
		}
		
		@DisplayName("Agregar historia, Prioridad <=0")
		@Test 
		public void storyAddTestPriorityThrowsException() {
			when(gameRepo.findById(game.getId())).thenReturn(Optional.of(game));
			story.setBusinessValue(new BigDecimal(9));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(-2));
			
			
			
			assertThrows(Exception.class,() -> {
	    		storyService.saveStory(story,game);
	    		
	    	});
			
			verifyZeroInteractions(storyRepo);
			
			
			
		}
		
		//Verificar si el juego asociado a la historia no existe, lance una excepcion
		@DisplayName("Agregar historia con juego inexistente")
		@Test 
		public void storyAddTestGameNullThrowsException() {
			
			story.setBusinessValue(new BigDecimal(9));
			story.setInitialSprint(new BigDecimal(3));
			story.setPriority(new BigDecimal(2));
			when(gameRepo.findById(game.getId())).thenReturn(null);
			

			assertThrows(NullPointerException.class,() -> {
	    		storyService.saveStory(story,game);
	    		
	    	});
			
			verifyZeroInteractions(storyRepo);

		}
		//Verificar que el juego asociado a la historia, exista
		@DisplayName("Agregar historia con juego existente")
		@Test 
		public void storyAddTestTopicNoNull() {
			
			
			story.setBusinessValue(new BigDecimal(9));
			story.setInitialSprint(new BigDecimal(3));
			story.setPriority(new BigDecimal(2));
		
			when(gameRepo.findById(game.getId())).thenReturn(Optional.of(game));
			
			try {
				assertEquals(storyService.saveStory(story,game),story);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
			verify(storyRepo,times(1)).save(story);
			
			
			
			
		}
		}


		@Nested
		class EditStory {
			
			public Optional<TsscStory> story1;
			@BeforeEach
			public void setUp2() {

				
				story1 = Optional.of(story);
				when(storyRepo.findById(story.getId())).thenReturn(story1);
			
			}
			
			@DisplayName("Editar historia inexistente")
			@Test 
			public void EditTestStoryNull() {
				when(gameRepo.findById(game.getId())).thenReturn(Optional.of(game));
				story.setBusinessValue(new BigDecimal(1));
				story.setInitialSprint(new BigDecimal(3));
				story.setPriority(new BigDecimal(8));
				
				when(storyRepo.findById(story.getId())).thenReturn(null);
				assertThrows(Exception.class,() -> {
		    		storyService.editStory(story,game);
		    	});
				
				verify(storyRepo,times(0)).save(story);
				
				
		}
			//Sprint Inicial, Valor de negocio y prioridad >0
			@DisplayName("Editar historia con Sprint Inicial, Valor de negocio y prioridad >0")
			@Test 
			public void storyEditTestNoThrowsException() {
				
				story.setBusinessValue(new BigDecimal(1));
				story.setInitialSprint(new BigDecimal(3));
				story.setPriority(new BigDecimal(8));

				when(gameRepo.findById(game.getId())).thenReturn(Optional.of(game));
				try {
					assertEquals(storyService.editStory(story,game),story);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					fail();
				}
			
				verify(storyRepo,times(1)).save(story);
		
			}
			//Sprint Inicial, Valor de negocio y prioridad <=0
			@DisplayName("Editar historia con Sprint Inicial, Valor de negocio y prioridad <=0")
			@Test 
			public void storyEditTestThrowsException() {
				
				story.setBusinessValue(new BigDecimal(-1));
				story.setInitialSprint(new BigDecimal(-3));
				story.setPriority(new BigDecimal(0));
				
				
				when(gameRepo.findById(game.getId())).thenReturn(Optional.of(game));
				assertThrows(Exception.class,() -> {
		    		storyService.editStory(story,game);
		    	});
			
				
				verify(storyRepo,times(0)).save(story);
				
				
				
			}
			
			@DisplayName("Editar historia con juego inexistente")
			@Test 
			public void storyEditTestGameNullThrowsException() {
				
				story.setBusinessValue(new BigDecimal(1));
				story.setInitialSprint(new BigDecimal(3));
				story.setPriority(new BigDecimal(8));
				
				
				when(gameRepo.findById(game.getId())).thenReturn(null);
				
				assertThrows(Exception.class,() -> {
		    		storyService.editStory(story,game);
		    		
		    	});
				
				verify(storyRepo,times(0)).save(story);

			}
			
			
			@DisplayName("Editar historia con juego inexistente")
			@Test 
			public void storyEditTestTopicNoNull() {
				
				story.setBusinessValue(new BigDecimal(1));
				story.setInitialSprint(new BigDecimal(3));
				story.setPriority(new BigDecimal(8));
				
			
				when(gameRepo.findById(game.getId())).thenReturn(Optional.of(game));
				try {
					assertEquals(storyService.editStory(story,game),story);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					fail();
				}
				verify(storyRepo,times(1)).findById(story.getId());
				verify(storyRepo,times(1)).save(story);
				
				
				
			}
		}
		


	}



