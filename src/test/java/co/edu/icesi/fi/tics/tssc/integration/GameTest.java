package co.edu.icesi.fi.tics.tssc.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.icesi.fi.tics.tssc.exceptions.CapacityException;
import co.edu.icesi.fi.tics.tssc.exceptions.SpringException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicException;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;
import co.edu.icesi.fi.tics.tssc.services.GameServiceImpl;
import co.edu.icesi.fi.tics.tssc.services.TopicServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class GameTest {

	@Autowired
	private GameServiceImpl gameService;

	@Autowired
	private TopicServiceImpl topicService;

	private TsscGame gameAux;

	private TsscTopic topicAux;

	@BeforeEach
	public void setUp() {

		gameAux = new TsscGame();
		gameAux.setNGroups(1);
		gameAux.setNSprints(1);
		topicAux = new TsscTopic();
		topicAux.setDefaultGroups(1);
		topicAux.setDefaultSprints(1);

		try {
			topicService.saveTopic(topicAux);
			gameService.saveGame(gameAux);
		} catch (TopicException | CapacityException | SpringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@DisplayName("Guardar Juego sin Tema")
	@Test
	public void testSaveGame() {
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);

		try {

			assertTrue(gameService.saveGame(game).equals(game));

		} catch (CapacityException | TopicException | SpringException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}

	@DisplayName("Guardar Juego Con Tema")
	@Test
	public void testSaveGameConTopic() {
		TsscGame game = new TsscGame();
		game.setName("Game Editado");
		game.setNGroups(1);
		game.setNSprints(1);

		try {

			assertTrue(gameService.saveGameWithTopic(game, topicAux.getId()).getName().equals("Game Editado"));

		} catch (CapacityException | TopicException | SpringException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}

	@DisplayName("Editar Tema")
	@Test
	public void testEditGame() {
		TsscGame game2 = new TsscGame();
		game2.setId(gameAux.getId());
		game2.setName("GameAux2");
		game2.setNGroups(32);
		game2.setNSprints(14);

		try {
			assertTrue(gameService.editGame(game2).getName().equals("GameAux2"));

		} catch (TopicException | CapacityException | SpringException e) {
			// TODO Auto-generated catch block
			fail();
			e.printStackTrace();
		}
	}

	// **************************
	// PUNTO D: MÉTODO REFACTOR
	// ***************************
	@DisplayName(" Punto D: Guardar Juego mediante un Tema")
	@Test
	public void testSaveGameMedianteTopic() {

		try {

			assertTrue(gameService.saveGameWithTopic2(topicAux).getNGroups() == 1);
			assertTrue(gameService.saveGameWithTopic2(topicAux).getNSprints() == 1);

		} catch (CapacityException | TopicException | SpringException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}

	@DisplayName(" Punto D: Guardar Juego mediante un Tema 2")
	@Test
	public void testSaveGameMedianteTopic2() {

		List<TsscTimecontrol> listTc = new ArrayList<TsscTimecontrol>();
		List<TsscStory> listSt = new ArrayList<TsscStory>();
		
		topicAux.setTsscStories(listSt);
		topicAux.setTsscTimecontrol(listTc);
		
		try {

			assertTrue(gameService.saveGameWithTopic2(topicAux).getTsscTimecontrols().containsAll(listTc));
			assertTrue(gameService.saveGameWithTopic2(topicAux).getTsscStories().containsAll(listSt));

		} catch (CapacityException | TopicException | SpringException e) {
			// TODO Auto-generated catch block
			fail();
		}
	}

}
