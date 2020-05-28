package co.edu.icesi.taller3.daotest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
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
import co.edu.icesi.taller3.model.TsscStory;
import co.edu.icesi.taller3.model.TsscTimecontrol;
import co.edu.icesi.taller3.model.TsscTopic;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/applicationContext.xml")
@SpringBootTest(classes = Taller3JPA.class)
@Rollback(false)
public class TestTsscGameDAO {

	@Autowired
	private ITsscGameDao gameDao;
	
	@Autowired
	private ITsscTopicDao topicDao;
	
	@Autowired
	private ITsscStoryDao storyDao;
	
	
	@Autowired
	private ITsscTimecontrolDao timeDao;
	
	private TsscGame game1;
	private TsscGame game2;
	private TsscGame game3;
	private TsscGame game4;
	private TsscTopic topic1;
	private TsscTopic topic2;
	
	public void setUp() {
		//gameDao.deleteAll();
		game1 = new TsscGame();
		game1.setNSprints(1);
		game1.setNGroups(1);
		game1.setName("Game1");
		gameDao.save(game1);
		System.out.println(game1.getId());
		System.out.println("total "+gameDao.findAll().size());
	}
	
	public void setUpQuerys1() {
		//gameDao.deleteAll();
		game2 = new TsscGame();
		game2.setNSprints(1);
		game2.setNGroups(1);
		game2.setName("Game2");
		game2.setScheduledDate(LocalDate.of(2020, 05, 10));
		game2.setScheduledTime(LocalTime.of(12, 00));
		topic1 = new TsscTopic();
		//List<TsscTopic> topics = new ArrayList<TsscTopic>();
		topicDao.save(topic1);
		game2.setTsscTopic(topic1);
		System.out.println("topicc "+game2.getTsscTopic().getId());
		gameDao.save(game2);
		
		
		System.out.println("Topic 2"+game2.getId());
	}
	public void setUpQuerys2_1() {
		gameDao.deleteAll();
		game1 = new TsscGame();
		game2 = new TsscGame();
		game3 = new TsscGame();
		game4 = new TsscGame();
		game1.setName("Game1");
		game2.setName("Game2");
		game3.setName("Game3");
		game4.setName("Game4");
		game1.setScheduledDate(LocalDate.of(2020, 05, 10));
		game2.setScheduledDate(LocalDate.of(2020, 05, 10));
		game3.setScheduledDate(LocalDate.of(2020, 05, 10));
		game4.setScheduledDate(LocalDate.of(2020, 04, 10));
		game1.setScheduledTime(LocalTime.of(12, 00));
		game2.setScheduledTime(LocalTime.of(11, 00));
		game3.setScheduledTime(LocalTime.of(12, 00));
		game4.setScheduledTime(LocalTime.of(12, 00));
		topic1 = new TsscTopic();
		topic2 = new TsscTopic();
		topic1.setName("Topic1");
		topic2.setName("Topic2");
		topic1.setTsscGames(new ArrayList<TsscGame>());
		topic2.setTsscGames(new ArrayList<TsscGame>());
		topic1.addTsscGame(game1);
		topic1.addTsscGame(game2);
		topic2.addTsscGame(game3);
		topic2.addTsscGame(game4);
		topicDao.save(topic1);
		topicDao.save(topic2);
		game1.setTsscTopic(topic1);
		game2.setTsscTopic(topic1);
		game3.setTsscTopic(topic2);
		game4.setTsscTopic(topic2);
		game1.setTsscStories(new ArrayList<TsscStory>());
		game2.setTsscStories(new ArrayList<TsscStory>());
		game3.setTsscStories(new ArrayList<TsscStory>());
		game4.setTsscStories(new ArrayList<TsscStory>());
		game1.setTsscTimecontrol(new ArrayList<TsscTimecontrol>());
		game2.setTsscTimecontrol(new ArrayList<TsscTimecontrol>());
		game3.setTsscTimecontrol(new ArrayList<TsscTimecontrol>());
		game4.setTsscTimecontrol(new ArrayList<TsscTimecontrol>());
		gameDao.save(game1);
		gameDao.save(game2);
		gameDao.save(game3);
		gameDao.save(game4);
		
		System.out.println("Topic 2"+game2.getId());
		
		for(int i=0; i<5;i++) {
			
			TsscStory story =new TsscStory();
			TsscTimecontrol control = new TsscTimecontrol();
			timeDao.save(control);
			storyDao.save(story);
			game1.addTsscStory(story);
			game1.addTsscTimecontrol(control);
			game3.addTsscTimecontrol(control);
			game4.addTsscStory(story);
		
		}
		
	for(int i=0; i<11;i++) {
			
			TsscStory story =new TsscStory();
			storyDao.save(story);
			game2.addTsscStory(story);
			game3.addTsscStory(story);
		}
	}




	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveGameTest() {

		assertNotNull(gameDao);
		
		TsscGame game = new TsscGame();
		game.setNSprints(1);
		game.setNGroups(1);
		game.setName("Game1");
		gameDao.save(game);
		
		
		
		
		gameDao.save(game);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateGameTest() {
		setUp();
		assertNotNull(gameDao);
		
		TsscGame game = gameDao.findById(game1.getId());
		assertNotNull(game);
		game.setName("hello");
		gameDao.update(game);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteGameTest() {
		setUp();
		assertNotNull(gameDao);
		
		TsscGame game = gameDao.findById(game1.getId());
		assertNotNull(game);
		gameDao.delete(game);
		
	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findGameByNameTest() {
		timeDao.deleteAll();
			storyDao.deleteAll();
			gameDao.deleteAll();
			
			setUpQuerys1();
			
			assertNotNull(gameDao);
			
			TsscGame findedGame = gameDao.findByName("Game2").get(0);
			assertEquals(game2,findedGame);
			assertTrue(gameDao.findByName("Game2").size()==1);

	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findGameByTopicIdTest() {
		timeDao.deleteAll();
			storyDao.deleteAll();
			gameDao.deleteAll();
			
			setUpQuerys1();
			
			assertNotNull(gameDao);
			
			TsscGame findedGame = gameDao.findByTopicId(topic1.getId()).get(0);
			assertEquals(game2,findedGame);
			assertTrue(gameDao.findByTopicId(topic1.getId()).size()==1);

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findGameByDateRangeTest() {
		timeDao.deleteAll();
		storyDao.deleteAll();
			gameDao.deleteAll();
		
			setUpQuerys1();
			
			assertNotNull(gameDao);
			
			TsscGame findedGame = gameDao.findByDateRange(LocalDate.of(2020, 04, 01), LocalDate.of(2020, 05, 20)).get(0);
			assertEquals(game2,findedGame);
			assertTrue(gameDao.findByDateRange(LocalDate.of(2020, 04, 01), LocalDate.of(2020, 05, 20)).size()==1);

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findGameByDateAndTimeRangeTest() {
		timeDao.deleteAll();
		storyDao.deleteAll();
			gameDao.deleteAll();
			
			setUpQuerys1();
			
			assertNotNull(gameDao);
			
			TsscGame findedGame = gameDao.findByDateHourRange(LocalDate.of(2020, 05, 10)
					, LocalTime.of(11, 00), LocalTime.of(12, 59)).get(0);
			assertEquals(game2,findedGame);
			assertTrue(gameDao.findByDateHourRange(LocalDate.of(2020, 05, 10)
					, LocalTime.of(11, 00), LocalTime.of(12, 59)).size()==1);

	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void getTopicByGameDateTest() {
		timeDao.deleteAll();
			storyDao.deleteAll();
			gameDao.deleteAll();
			topicDao.deleteAll();
			
		
			setUpQuerys2_1();
			
			assertNotNull(gameDao);
			System.out.println("essoooo");
			TsscTopic findedTopic1 = (TsscTopic)gameDao.getTopicByGameDate(LocalDate.of(2020, 05, 10)).get(0)[0];
			int size =gameDao.getTopicByGameDate(LocalDate.of(2020, 05, 10)).size();
			int countGamesTopic1= ((Number) gameDao.getTopicByGameDate(LocalDate.of(2020, 05, 10)).get(0)[1]).intValue();
			int countGamesTopic2= ((Number) gameDao.getTopicByGameDate(LocalDate.of(2020, 05, 10)).get(1)[1]).intValue();
			
			
			assertEquals(topic1,findedTopic1);
			//VERIFICAR QUE EL TOPIC 1 TIENE 2 JUEGOS EN ESE FECHA
			assertEquals(2,countGamesTopic1);
			//VERIFICAR QUE EL TOPIC 2 TIENE 1 JUEGO EN ESE FECHA
			assertEquals(1,countGamesTopic2);
			//VERIFICAR QUE HAY DOS TOPICS QUE TIENEN JUEGOS EN LA FECHA DADA
			assertEquals(2,size);

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findGameLess10Stories() {
		gameDao.deleteAll();
		topicDao.deleteAll();
		storyDao.deleteAll();
		timeDao.deleteAll();
		assertNotNull(gameDao);
		assertNotNull(topicDao);
		assertNotNull(storyDao);
		assertNotNull(timeDao);
		setUpQuerys2_1();
		List<TsscGame> games  = gameDao.findGameLess10StoriesOrNoTimeControl(LocalDate.of(2020, 05, 10));
		
		//Game 1 have 5 stories and 5 timecontrols in the given date  (cumple restriccion de historias  y fecha, cumple)
		//Game 2 have 11 stories and doesnt have timecontrols  (cumple restriccion de cronometros y fecha, cumple)
		assertThat(games, CoreMatchers.hasItems(game1,game2));
		
		//Game 4 have 5 stories and doesnt have timecontrols
		//  but no in the given date  (cumple las dos restricciones pero no cumple con la fecha, no cumple)
		assertTrue(!games.contains(game4));
		
		//Game 3 have 11 stories and have 5 timescontrols in a given date 
		// (no cumple con ninguna de las restricciones aunque tenga la fecha dada, no cumple) 
		assertTrue(!games.contains(game3));
		
		
		
		for(int i=0;i<games.size();i++) {
			
			System.out.println("Gamee "+games.get(i).getName());
		}
	}
	
	

	

	
		
		
		
		
		
		
		
		
	


}
