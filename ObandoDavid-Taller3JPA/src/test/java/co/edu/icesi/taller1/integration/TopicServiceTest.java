package co.edu.icesi.taller1.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.taller3.Taller3JPA;
import co.edu.icesi.taller3.model.TsscTopic;
import co.edu.icesi.taller3.service.TopicServiceImp;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Taller3JPA.class)
class TopicServiceTest {

	
	@Autowired
	public TopicServiceImp topicService;
	
	public TsscTopic topic;

	@BeforeEach
	public void setUp1() {
		
		topic = new TsscTopic();

	}
	
	@DisplayName("Guardar Tema")
	@Test 
	public void testSaveTopic() {
		
		topic.setDefaultSprints(5);
		topic.setDefaultGroups(10);
		TsscTopic topic1 = null;
		try {
			topic1= topicService.saveTopic(topic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
		assertEquals(topic1,topic);
		assertTrue(topic1.getDefaultGroups()>0);
		assertTrue(topic1.getDefaultSprints()>0);
		assertTrue(topicService.sizeTopics()==1);
		
	}
	@DisplayName("Editar Tema")
	@Test 
	public void testEditTopic() {
		
		topic.setDefaultGroups(2);
		topic.setDefaultSprints(2);
		topic.setName("Topic 1");
		
		try {
			topicService.saveTopic(topic);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			assertTrue(topicService.editTopic(topic).getName().equals("Topic 1"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			fail();
		}
		
		topic.setName("Topic 2");
		topic.setDescription("hello");
		
		try {
			assertNotNull(topicService.editTopic(topic));
			assertTrue(topicService.editTopic(topic).getName().equals("Topic 2"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}

		
		
		
	}

	


}
