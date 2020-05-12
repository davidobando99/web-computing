package co.edu.icesi.fi.tics.tssc.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.sun.xml.bind.v2.schemagen.xmlschema.Annotation;

import co.edu.icesi.fi.tics.tssc.exceptions.*;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repositories.IGameRepository;
import co.edu.icesi.fi.tics.tssc.repositories.IStoryRepository;
import co.edu.icesi.fi.tics.tssc.services.StoryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class StoryTest {

	@Mock
	private IGameRepository gameRepository;

	@Mock
	private IStoryRepository storyRepository;

	@InjectMocks
	private StoryServiceImpl storyService;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	// *********************************************
	// PRUEBAS PARA EL GUARDAR
	// *********************************************

	// ************************************
	// *** Pruebas excepciones atributos***
	// ************************************

	// Se encarga de verificar que se lance la excepción
	// cuando el valor de negocio es igual a cero.
	@DisplayName("Valor Negocio Igual A Cero")
	@Test
	public void testValorNegocioIgualACero() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(0));
		story.setInitialSprint(new BigDecimal(10));
		story.setPriority(new BigDecimal(40));

		long id = 0;
		Mockito.when(gameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));

		assertThrows(BusinessValueException.class, () -> {
			storyService.saveStory(story, id);
		});

		Mockito.verifyNoInteractions(storyRepository);
	}

	// Se encarga de verificar que se lance la excepción
	// cuando el valor de InitialSprint es igual a cero.
	@DisplayName("Sprint Inicial Igual A Cero")
	@Test
	public void testSprintInitialIgualACero() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(47));
		story.setInitialSprint(new BigDecimal(0));
		story.setPriority(new BigDecimal(40));

		long id = 0;
		Mockito.when(gameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));

		assertThrows(InitialSprintException.class, () -> {
			storyService.saveStory(story, id);
		});

		Mockito.verifyNoInteractions(storyRepository);

	}

	// Se encarga de verificar que se lance la excepción
	// cuando el valor de Priority es igual a cero.
	@DisplayName("Priority Igual A Cero")
	@Test
	public void testPriorityIgualACero() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(47));
		story.setInitialSprint(new BigDecimal(50));
		story.setPriority(new BigDecimal(0));

		long id = 0;
		Mockito.when(gameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));

		assertThrows(PriorityException.class, () -> {
			storyService.saveStory(story, id);
		});

		Mockito.verifyNoInteractions(storyRepository);
	}

	// Se encarga de verificar que se lance la excepción
	// cuando el valor de negocio es menor a cero.
	@DisplayName("Valor Negocio Menor A Cero")
	@Test
	public void testValorNegocioMenorACero() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(-1));
		story.setInitialSprint(new BigDecimal(10));
		story.setPriority(new BigDecimal(40));

		long id = 0;
		Mockito.when(gameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));

		assertThrows(BusinessValueException.class, () -> {
			storyService.saveStory(story, id);
		});

		Mockito.verifyNoInteractions(storyRepository);
	}

	// Se encarga de verificar que se lance la excepción
	// cuando el valor de InitialSprint es menor a cero.
	@DisplayName("Sprint Inicial Menor A Cero")
	@Test
	public void testSprintInitialMenorACero() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(47));
		story.setInitialSprint(new BigDecimal(-1));
		story.setPriority(new BigDecimal(40));

		long id = 0;
		Mockito.when(gameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));

		assertThrows(InitialSprintException.class, () -> {
			storyService.saveStory(story, id);
		});

		Mockito.verifyNoInteractions(storyRepository);

	}

	// Se encarga de verificar que se lance la excepción
	// cuando el valor de Priority es menor a cero.
	@DisplayName("Priority Menor A Cero")
	@Test
	public void testPriorityMenorACero() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(47));
		story.setInitialSprint(new BigDecimal(50));
		story.setPriority(new BigDecimal(-1));

		long id = 0;
		Mockito.when(gameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));

		assertThrows(PriorityException.class, () -> {
			storyService.saveStory(story, id);
		});
		Mockito.verifyNoInteractions(storyRepository);
	}

	// Se encarga de testear que lance la excepción, GameException, al guardar una
	// Historia con un juego que no existe
	// en la data-base.
	@DisplayName("Historia con Juego Inexistente")
	@Test
	public void testSaveStoryGameException() {
		long id = 2;
		TsscStory story = new TsscStory();

		when(gameRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(GameException.class, () -> {
			storyService.saveStory(story, id);
		});

		Mockito.verifyNoInteractions(storyRepository);
	}

	// Se encarga de testear que cuando se guarde una historia nula, lance la
	// excepción.
	@DisplayName("Guardar Historia null")
	@Test
	public void testSaveStoryNull() {
		assertThrows(StoryException.class, () -> {
			storyService.saveStory(null, (long) 5);
		});

		Mockito.verifyNoInteractions(storyRepository);

	}

	// ***********************
	// ** Proceso Guardar Historia con juego
	// ***********************

	// Se encarga de testear que en realidad la historia si guarda
	// con un Juego existente.
	@DisplayName("Guardar Historia con Juego Existente")
	@Test
	public void testSaveStoryConJuego() {

		long id = 1;
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(47));
		story.setInitialSprint(new BigDecimal(50));
		story.setPriority(new BigDecimal(4));

		Mockito.when(storyRepository.save(story)).thenReturn(story);
		Mockito.when(gameRepository.findById(id)).thenReturn(Optional.of(new TsscGame()));

		try {
			assertTrue(storyService.saveStory(story, id).equals(story));
		} catch (StoryException | GameException | BusinessValueException |  InitialSprintException
				| PriorityException e) {
			// TODO Auto-generated catch block
			fail();
		}

		Mockito.verify(storyRepository, times(1)).save(story);
	}

	// *************************************
	// PRUEBAS PARA EL EDITAR
	// **************************************

	// Verifica si el método lanza la excepcion cuando se intenta actualizar una
	// historia inexistente en la database.
	@DisplayName("Editar Historia Inexistente")
	@Test
	public void testEditStoryException() {

		TsscStory story = new TsscStory();

		Mockito.when(storyRepository.findById(Mockito.anyLong())).thenReturn(null);

		assertThrows(StoryException.class, () -> {
			storyService.editStory(story);
		});

		Mockito.verify(storyRepository, times(1)).findById(Mockito.anyLong());

	}

	// Verifica si el método lanza la excepcion cuando se intenta
	// actualizar una historia nula.
	@DisplayName("Editar Historia null")
	@Test
	public void testEditStoryExceptionNull() {
		assertThrows(StoryException.class, () -> {
			storyService.editStory(null);
		});

		Mockito.verifyNoInteractions(storyRepository);

	}

	// Verifica si el método edita de manera correcta cuando se intenta actualizar
	// una historia existente en la database.
	@DisplayName("Editar Historia Correctamente")
	@Test
	public void testEditStoryNotException() {

		try {

			// Por el cual voy a editar.
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(47));
			story.setInitialSprint(new BigDecimal(43));
			story.setPriority(new BigDecimal(40));

			Optional<TsscStory> list = Optional.of(story);

			Mockito.when(storyRepository.save(Mockito.any())).thenReturn(story);
			Mockito.when(storyRepository.findById(story.getId())).thenReturn(list);

			assertTrue(storyService.editStory(story).equals(story));

			verify(storyRepository, times(1)).save(story);

		} catch (Exception e) {
			fail();
		}

	}

}
