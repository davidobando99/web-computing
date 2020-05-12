package co.edu.icesi.fi.tics.tssc.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.edu.icesi.fi.tics.tssc.exceptions.CapacityException;
import co.edu.icesi.fi.tics.tssc.exceptions.SpringException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicException;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repositories.ITopicRepository;
import co.edu.icesi.fi.tics.tssc.services.TopicService;
import co.edu.icesi.fi.tics.tssc.services.TopicServiceImpl;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.testng.annotations.BeforeTest;

@RunWith(MockitoJUnitRunner.class)
class TopicTest {

	@Mock
	private ITopicRepository topicRepository;

	@InjectMocks
	private TopicServiceImpl topicService;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	// *********************************************
	// PRUEBAS PARA EL GUARDAR
	// *********************************************

	// Se encarga de verificar que lance la excepcion cuando se quiera
	// guardar un topic nulo.
	@DisplayName("Topic null")
	@Test
	public void testTopicNull() {
		assertThrows(TopicException.class, () -> {
			topicService.saveTopic(null);
		});

		Mockito.verifyNoInteractions(topicRepository);
	}

	// Se encarga de verificar si lanza la excepcion al tener un topic con Sprints
	// igual a cero.
	@DisplayName("Sprints Igual A Cero")
	@Test
	public void testSprintsExceptionIgualACero() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultSprints(0);
		topic.setDefaultGroups(3);

		assertThrows(SpringException.class, () -> {
			topicService.saveTopic(topic);
		});

		Mockito.verifyNoInteractions(topicRepository);
	}

	// Se encarga de verificar si lanza la excepcion al tener un topic con Sprints
	// menor a cero.
	@DisplayName("Sprints Menor A Cero")
	@Test
	public void testSprintsExceptionMenorACero() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultSprints(-1);
		topic.setDefaultGroups(3);

		assertThrows(SpringException.class, () -> {
			topicService.saveTopic(topic);
		});

		Mockito.verifyNoInteractions(topicRepository);
	}

	// Se encarga de testear si un topic guarda correctamente con Sprints correctos.
	@DisplayName("Topic Guardado Sprints")
	@Test
	public void testSprintsNotException() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultSprints(1);
		topic.setDefaultGroups(2);

		when(topicRepository.save(topic)).thenReturn(topic);
		try {
			assertTrue(topicService.saveTopic(topic).equals(topic));
		} catch (CapacityException | TopicException | SpringException e) {
			// TODO Auto-generated catch block
			fail();
		}
		verify(topicRepository, times(1)).save(topic);

	}

	// Se encarga de verificar si lanza la excepcion al tener un topic con Groups
	// igual a cero.
	@DisplayName("Groups Igual A Cero")
	@Test
	public void testGroupsExceptionIgualACero() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(0);
		topic.setDefaultSprints(3);

		assertThrows(CapacityException.class, () -> {
			topicService.saveTopic(topic);
		});

		Mockito.verifyNoInteractions(topicRepository);

	}

	// Se encarga de verificar si lanza la excepcion al tener un topic con Groups
	// Menor a cero.
	@DisplayName("Groups Menor A Cero")
	@Test
	public void testGroupsExceptionMenorACero() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(-6);
		topic.setDefaultSprints(3);

		assertThrows(CapacityException.class, () -> {
			topicService.saveTopic(topic);
		});

		Mockito.verifyNoInteractions(topicRepository);

	}

	// Se encarga de testear si un topic guarda correctamente con Group correctos.
	@DisplayName("Topic Guardado Group")
	@Test
	public void testGroupsNotException() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);

		when(topicRepository.save(topic)).thenReturn(topic);
		try {
			assertTrue(topicService.saveTopic(topic).equals(topic));
		} catch (CapacityException | TopicException | SpringException e) {
			// TODO Auto-generated catch block
			fail();
		}
		verify(topicRepository, times(1)).save(topic);
	}

	// *************************************
	// PRUEBAS PARA EDITAR
	// *************************************

	// Verifica si el método lanza la excepcion cuando se intenta actualizar un
	// topic inexistente en la database.
	@DisplayName("Editar Topic Inexistente")
	@Test
	public void testEditTopicException() {

		TsscTopic topic2 = new TsscTopic();
		topic2.setDefaultGroups(5);
		topic2.setDefaultSprints(10);

		Mockito.when(topicRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		assertThrows(TopicException.class, () -> {
			topicService.editTopic(topic2);
		});

		Mockito.verify(topicRepository, times(1)).findById(Mockito.anyLong());
	}

	// Verifica si el método lanza la excepcion cuando se intenta
	// actualizar un topic nulo.
	@DisplayName("Editar Topic null")
	@Test
	public void testEditTopicExceptionNull() {
		assertThrows(TopicException.class, () -> {
			topicService.editTopic(null);
		});

		Mockito.verifyNoInteractions(topicRepository);

	}

	// Verifica si el método edita de manera correcta cuando se intenta actualizar
	// un topic existente en la database.
	@DisplayName("Editar Topic Correcto")
	@Test
	public void testEditTopicNotException() {

		try {

			// Por el cual voy a editar.
			TsscTopic topic2 = new TsscTopic();
			topic2.setDefaultGroups(35);
			topic2.setDefaultSprints(50);

			Optional<TsscTopic> list = Optional.of(topic2);

			Mockito.when(topicRepository.save(Mockito.any())).thenReturn(topic2);
			Mockito.when(topicRepository.findById(topic2.getId())).thenReturn(list);

			try {
				assertTrue(topicService.editTopic(topic2).equals(topic2));
			} catch (CapacityException | SpringException e) {
				// TODO Auto-generated catch block
				fail();
			}

			verify(topicRepository, times(1)).save(topic2);

		} catch (TopicException e) {
			fail();
		}

	}

	// Se encarga de que lance excepcion cuando se edite un topic con
	// groups con errores de guardado.

	@DisplayName("Editar Topic sin atributos correctos. (Groups)")
	@Test
	public void testEditGameError() {

		TsscTopic topic = new TsscTopic();
		when(topicRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new TsscTopic()));
		topic.setDefaultGroups(-20);

		assertThrows(CapacityException.class, () -> {
			topicService.editTopic(topic);
		});
	}

	// Se encarga de que lance excepcion cuando se edite un topic con
	// sprints con errores de guardado.

	@DisplayName("Editar Topic sin atributos correctos. (Sprints)")
	@Test
	public void testEditGameError2() {

		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		when(topicRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new TsscTopic()));
		topic.setDefaultSprints(-20);

		assertThrows(SpringException.class, () -> {
			topicService.editTopic(topic);
		});
	}

}
