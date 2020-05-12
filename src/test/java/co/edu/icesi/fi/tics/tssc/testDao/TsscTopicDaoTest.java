package co.edu.icesi.fi.tics.tssc.testDao;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.TallerPersistenciaApplication;
import co.edu.icesi.fi.tics.tssc.dao.ITsscGameDAO;
import co.edu.icesi.fi.tics.tssc.dao.ITsscTopicDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@SpringBootTest(classes = TallerPersistenciaApplication.class)
@RunWith(SpringRunner.class)
@Rollback(false)
public class TsscTopicDaoTest {

	@Autowired
	private ITsscTopicDAO topicDao;
	
	@Autowired
	private ITsscGameDAO gameDao;
	
	private TsscTopic topic1;
	
	private TsscTopic topic2;
	
	
	public void setUp1() {
		
		topic1 = new TsscTopic();
		topic1.setDefaultGroups(4);
		topic1.setDefaultSprints(4);
		topic1.setName("topic1");
		topic1.setGroupPrefix("Prefix1");
		topic1.setDescription("Descripcion topic1");
		
		topicDao.save(topic1);
	}
	
	public void escenario2() {
		
		TsscGame game1 = new TsscGame();
		game1.setName("Juego1");
		game1.setNGroups(8);
		game1.setNSprints(8);
		game1.setScheduledDate(LocalDate.of(2020, 12, 14));
		game1.setScheduledTime(LocalTime.of(13, 5));

		TsscGame game2 = new TsscGame();
		game2.setName("Juego2");
		game2.setNGroups(1);
		game2.setNSprints(1);
		game2.setScheduledDate(LocalDate.of(2020, 12, 14));
		game2.setScheduledTime(LocalTime.of(15, 10));
		
		
		topic1 = new TsscTopic();
		topic1.setDefaultGroups(4);
		topic1.setDefaultSprints(4);
		topic1.setName("topic1");
		topic1.setGroupPrefix("Prefix1");
		topic1.setDescription("Descripcion topic1");
		
		topic2 = new TsscTopic();
		topic2.setDefaultGroups(4);
		topic2.setDefaultSprints(4);
		topic2.setName("topic2");
		topic2.setGroupPrefix("Prefix2");
		topic2.setDescription("Descripcion topic2");
		
		game1.setTsscTopic(topic1);
		game2.setTsscTopic(topic2);
		
		topic1.setTsscGames(new ArrayList<TsscGame>());
		topic2.setTsscGames(new ArrayList<TsscGame>());
		
		topic1.getTsscGames().add(game1);
		topic1.getTsscGames().add(game2);
		
		topic2.getTsscGames().add(game1);
		
		gameDao.save(game1);
		gameDao.save(game2);
		
		 topicDao.save(topic1);		
		 topicDao.save(topic2);

		
	}


	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveTopic() {
		assertNotNull(topicDao);

		TsscTopic topicP = new TsscTopic();
		topicP.setName("topicP");
		topicP.setDefaultGroups(4);
		topicP.setDefaultSprints(4);
		topicDao.save(topicP);

		assertNotNull(topicDao.findById(topicP.getId()));
		assertEquals("topicP", topicDao.findById(topicP.getId()).get(0).getName());

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateTopic() {
		setUp1();
		assertNotNull(topicDao);

		TsscTopic topic2 = new TsscTopic();
		topic2.setName("topic2");
		topic2.setDefaultGroups(20);
		topic2.setDefaultSprints(20);

		topicDao.update(topic2);

		assertNotNull(topicDao.findByName("topic2"));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteTopic() {
		setUp1();
		assertNotNull(topicDao);
		topicDao.delete(topic1);
		assertTrue(topicDao.findAll().size() == 0);

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByIdTopic() {
		setUp1();
		assertNotNull(topicDao);
		
		assertEquals(1, topicDao.findById(topic1.getId()).size());
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByNameTopic() {
	  assertNotNull(topicDao);
	  gameDao.deleteAll();
	  topicDao.deleteAll();
	  setUp1();
	  
	
	  assertTrue(topicDao.findByName("topic1").size() == 1);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDescriptionTopic() {
		assertNotNull(topicDao);
		gameDao.deleteAll();
		topicDao.deleteAll();
	     setUp1();
	  
	  
	  assertTrue(topicDao.findByDescription("Descripcion topic1").size() == 1);
		
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindTopicByScheduledGames() {
		
		try {
			
		assertNotNull(topicDao);
		assertNotNull(gameDao);
		topicDao.deleteAll();
		gameDao.deleteAll();
		
		escenario2();

		assertEquals(2, gameDao.findTopicByScheduledGames(LocalDate.of(2020, 12, 14)).size());
		
		}catch(Exception e) {
			
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	@DisplayName("Test Verificar ordenamiento por horas")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByTopicByScheduledGamesOrganizedByHours() {
		
		try {
			
			assertNotNull(topicDao);
			assertNotNull(gameDao);
			topicDao.deleteAll();
			gameDao.deleteAll();
			
			escenario2();
			
			//Debido al esceneario 2 debería guardar el topic1 de primero.
			
			TsscTopic prueba = (TsscTopic) gameDao.findTopicByScheduledGames(LocalDate.of(2020, 12, 14)).get(0)[0];
			
			assertEquals(topic1.getName(), prueba.getName());
			
			}catch(Exception e) {
				
				e.printStackTrace();
				fail();
			}
		
		
	}
	
}
