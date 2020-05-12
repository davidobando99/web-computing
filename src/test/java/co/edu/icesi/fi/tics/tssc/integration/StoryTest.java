package co.edu.icesi.fi.tics.tssc.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.icesi.fi.tics.tssc.exceptions.BusinessValueException;
import co.edu.icesi.fi.tics.tssc.exceptions.CapacityException;
import co.edu.icesi.fi.tics.tssc.exceptions.GameException;
import co.edu.icesi.fi.tics.tssc.exceptions.InitialSprintException;
import co.edu.icesi.fi.tics.tssc.exceptions.PriorityException;
import co.edu.icesi.fi.tics.tssc.exceptions.SpringException;
import co.edu.icesi.fi.tics.tssc.exceptions.StoryException;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.services.GameServiceImpl;
import co.edu.icesi.fi.tics.tssc.services.StoryServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class StoryTest {

	@Autowired
	private StoryServiceImpl storyService;

	@Autowired
	private GameServiceImpl gameService;

	private TsscStory storyAux;

	private TsscGame gameAux;

	@BeforeEach
	public void setUp() {

		storyAux = new TsscStory();
		storyAux.setInitialSprint(new BigDecimal(1));
		storyAux.setPriority(new BigDecimal(1));
		storyAux.setBusinessValue(new BigDecimal(1));

		gameAux = new TsscGame();
		gameAux.setNGroups(1);
		gameAux.setNSprints(1);

		try {
			gameService.saveGame(gameAux);
			storyService.saveStory(storyAux, gameAux.getId());
		} catch (StoryException | GameException | BusinessValueException | InitialSprintException | PriorityException
				| CapacityException | SpringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@DisplayName("Guardar una Historia")
	@Test
	public void testSaveStory() {

		TsscStory story = new TsscStory();
		story = new TsscStory();
		story.setInitialSprint(new BigDecimal(2));
		story.setPriority(new BigDecimal(2));
		story.setBusinessValue(new BigDecimal(2));

		try {
			assertTrue(storyService.saveStory(story, gameAux.getId()).equals(story));
		} catch (StoryException | GameException | BusinessValueException | InitialSprintException
				| PriorityException e) {
			// TODO Auto-generated catch block
			fail();
		}

	}

	@DisplayName("Editar una Historia")
	@Test
	public void testEditStory() {
		
		TsscStory story = new TsscStory();
		story.setId(storyAux.getId());
		story.setAltDescripton("Soy la editada");
		story.setInitialSprint(new BigDecimal(2));
		story.setPriority(new BigDecimal(2));
		story.setBusinessValue(new BigDecimal(2));
		
		try {
			assertTrue(storyService.editStory(story).getAltDescripton().equals("Soy la editada"));
			
		}catch(StoryException e) {
			fail();
		}
		

	}

}
