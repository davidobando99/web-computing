package co.edu.icesi.taller3.daotest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.taller3.Taller3JPA;
import co.edu.icesi.taller3.dao.ITsscGameDao;
import co.edu.icesi.taller3.dao.ITsscStoryDao;
import co.edu.icesi.taller3.dao.ITsscTimecontrolDao;
import co.edu.icesi.taller3.dao.ITsscTopicDao;
import co.edu.icesi.taller3.model.TsscGame;
import co.edu.icesi.taller3.model.TsscTopic;

@SpringBootTest(classes = Taller3JPA.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
class TestTsscTopicDAO {

	@Autowired
	private ITsscTopicDao topicDao;
	
	private TsscTopic topic1;
	private TsscTopic topic2;
	
	@Autowired
	private ITsscGameDao gameDao;
	
	@Autowired
	private ITsscStoryDao storyDao;
	
	
	@Autowired
	private ITsscTimecontrolDao timeDao;

	


	
	public void setUp() {
		topicDao.deleteAll();
		topic1 = new TsscTopic();
		topic1.setDefaultSprints(1);
		topic1.setDefaultGroups(1);
		topic1.setDescription("Tema1");
		topic1.setGroupPrefix("100");
		topic1.setName("Topic1");
		topicDao.save(topic1);
		System.out.println(topic1.getId());
		System.out.println("total "+topicDao.findAll().size());
	}
	
	public void setUpQuerys() {
		topicDao.deleteAll();
		topic2 = new TsscTopic();
		topic2.setDefaultSprints(1);
		topic2.setDefaultGroups(1);
		topic2.setDescription("Tema2");
		topic2.setGroupPrefix("100");
		topic2.setName("Topic2");
		topicDao.save(topic2);
		System.out.println("Topic 2"+topic2.getId());
	}

	

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTopicTest() {

		assertNotNull(topicDao);
		
		TsscTopic topic = new TsscTopic();
		topic.setDefaultSprints(1);
		topic.setDefaultGroups(1);
		topic.setDescription("Tema");
		topic.setGroupPrefix("100");
		topic.setName("Topic");
		
		
		
		topicDao.save(topic);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTopicTest() {
		setUp();
		assertNotNull(topicDao);
		
		TsscTopic topic = topicDao.findById(topic1.getId());
		assertNotNull(topic);
		topic.setDescription("TopicNew");
		topicDao.update(topic);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTopicTest() {
		setUp();
		assertNotNull(topicDao);
		
		TsscTopic topic = topicDao.findById(topic1.getId());
		assertNotNull(topic);
		topicDao.delete(topic);
		
	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTopicByNameTest() {
		timeDao.deleteAll();
		storyDao.deleteAll();
			gameDao.deleteAll();
			topicDao.deleteAll();
			setUpQuerys();
			
			assertNotNull(topicDao);
			
			TsscTopic findedTopic = topicDao.findByName("Topic2").get(0);
			assertEquals(topic2,findedTopic);
			assertTrue(topicDao.findByName("Topic2").size()==1);

	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTopicByDescriptionTest() {
		timeDao.deleteAll();
		storyDao.deleteAll();
			gameDao.deleteAll();
			topicDao.deleteAll();
			setUpQuerys();
			
			assertNotNull(topicDao);
			
			TsscTopic findedTopic = topicDao.findByDescription("Tema2").get(0);
			assertEquals(topic2,findedTopic);
			assertTrue(topicDao.findByDescription("Tema2").size()==1);

	}
	
//	@Test
//	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public void getTopicByGameDateTest() {
//			gameDao.deleteAll();
//			topicDao.deleteAll();
//			setUpQuerys2();
//			
//			assertNotNull(gameDao);
//			
//			TsscTopic findedTopic = (TsscTopic)topicDao.getTopicByGameDate(LocalDate.of(2020, 05, 10)).get(0)[0];
//			System.out.println("hola "+ findedTopic.getName());
//			
//
//	}
	
	
		
		
		
		
		
		
		
		
	


}
