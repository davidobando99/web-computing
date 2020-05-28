package co.edu.icesi.taller1.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.beust.jcommander.internal.Lists;

import co.edu.icesi.taller2.model.TsscTopic;
import co.edu.icesi.taller2.repository.TopicRepository;
import co.edu.icesi.taller2.service.TopicServiceImp;

@RunWith(MockitoJUnitRunner.class)
@Nested
class TopicServiceTest {

	@Mock
	public TopicRepository topicRepo;
	
	
	@InjectMocks
	public TopicServiceImp topicService;
	
	public TsscTopic topic;

	@BeforeEach
	public void setUp1() {
		MockitoAnnotations.initMocks(this);
		topic = new TsscTopic();
	
		
		
	}
	@Nested
	class AddTopic {
	@DisplayName("Agregar tema null")
	@Test 
	public void testAddTopicNullThrowsException() {
		
		
		
		
		
		assertThrows(Exception.class,() -> {
    		topicService.saveTopic(null);
    	});
		verifyZeroInteractions(topicRepo);
		
	}
	@DisplayName("Agregar tema con Sprints <=0")
	@Test 
	public void topicAddTestSprintsThrowsException() {
		
		topic.setDefaultSprints(1);
		topic.setDefaultGroups(0);
		
		
		
		assertThrows(Exception.class,() -> {
    		topicService.saveTopic(topic);
    	});
		verifyZeroInteractions(topicRepo);
		
	}
	
	@DisplayName("Agregar tema con Sprints y Groups >0")
	@Test 
	public void topicAddTestSprintsNoThrowsException() {
		//TsscTopic topic = new TsscTopic();
		topic.setDefaultSprints(1);
		topic.setDefaultGroups(1);
		when(topicRepo.save(topic)).thenReturn(topic);
		try {
			assertTrue(topicService.saveTopic(topic).equals(topic));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
		verify(topicRepo,times(1)).save(topic);
		
		
		
	}
	@DisplayName("Agregar tema con Groups <=0")
	@Test 
	public void topicAddTestGroupsThrowsException() {
		
		topic.setDefaultGroups(0);
		topic.setDefaultSprints(1);
		
		
		
		assertThrows(Exception.class,() -> {
    		topicService.saveTopic(topic);
    		
    	});
		
		verifyZeroInteractions(topicRepo);
		
		
		
	}

}

	@Nested
	class EditTopic {
		
		@BeforeEach
		public void setUp2() {

			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			
			when(topicRepo.save(topic)).thenReturn(topic);
			
		
		}
		
		@DisplayName("Editar tema que no existe")
		@Test 
		public void testEditTopicNullThrowsException() {
			
			TsscTopic topic2 = new TsscTopic();
			topic.setId(2);
			
			
			assertThrows(Exception.class,() -> {
	    		topicService.editTopic(topic2);
	    	});
			
			verify(topicRepo,times(0)).save(topic2);
			
			
		}
		//Sprints And Groups >0
		@DisplayName("Editas tema con Sprints y Groups >0")
		@Test 
		public void topicEditTestNoThrowsException() {
			Optional<TsscTopic> topic1 = Optional.of(topic);
			topic.setDefaultGroups(2);
			topic.setDefaultSprints(2);

			when(topicRepo.findById(topic.getId())).thenReturn(topic1);
			try {
				assertEquals(topicService.editTopic(topic),topic);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail();
			}
			
			
		}
		//Sprints And Groups <=0
		@DisplayName("Editas tema con Sprints y Groups <=0")
		@Test 
		public void topicEditTestThrowsException() {
			
			topic.setDefaultGroups(0);
			topic.setDefaultSprints(-1);
			assertThrows(Exception.class,() -> {
	    		topicService.editTopic(topic);
	    	});
		
			verify(topicRepo,times(0)).save(topic);
			
			
			
			
		}
	}
	
	
	
	
	
	
	

}
