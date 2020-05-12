package co.edu.icesi.fi.tics.tssc.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AbstractContextLoader;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.services.TopicService;
import co.edu.icesi.fi.tics.tssc.services.TopicServiceImpl;
import co.edu.icesi.fi.tics.tssc.exceptions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TopicTest{

	@Autowired
	private TopicServiceImpl topicService;

	private TsscTopic topicAux;
	
	@BeforeEach
	public void setUp() {

		topicAux = new TsscTopic();
		topicAux.setName("TopicAux");
		topicAux.setDefaultGroups(1);
		topicAux.setDefaultSprints(1);
		
		
		try {
			topicService.saveTopic(topicAux);
		} catch (TopicException | CapacityException | SpringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@DisplayName("Guardar Topic")
	@Test
	public void testSaveTopic() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);

		try {

			assertTrue(topicService.saveTopic(topic).equals(topic));

		} catch (CapacityException | TopicException | SpringException e) {
			// TODO Auto-generated catch block
			fail();
		}

	}
	
	@DisplayName("Editar Topic")
	@Test
	public void testEditTopic() {

		TsscTopic topic2 = new TsscTopic();
		topic2.setId(topicAux.getId());
		topic2.setName("TopicAux2");
		topic2.setDefaultGroups(32);
		topic2.setDefaultSprints(14);
		
		try {
			assertTrue(topicService.editTopic(topic2).getName().equals("TopicAux2"));
			
		} catch (TopicException | CapacityException | SpringException e) {
			// TODO Auto-generated catch block
			fail();
			e.printStackTrace();
		}
		
	}


}
