package co.edu.icesi.fi.tics.tssc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.exceptions.CapacityException;
import co.edu.icesi.fi.tics.tssc.exceptions.GameException;
import co.edu.icesi.fi.tics.tssc.exceptions.SpringException;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.services.GameService;

@RestController
public class GameRestController {

	private GameService gameService;

	@Autowired
	public GameRestController(GameService gameService) {
		// TODO Auto-generated constructor stub
		this.gameService = gameService;
	}

	@PostMapping("/games")
	public TsscGame saveGame(TsscGame nuevo) {

		try {
			if (nuevo.getTsscTopic() == null) {

				return gameService.saveGame(nuevo);

			} else {

				return gameService.saveGameWithTopic(nuevo, nuevo.getTsscTopic().getId());
			}

		} catch (GameException | CapacityException | SpringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@PostMapping("/games/{id}")
	public TsscGame editGame(@PathVariable("id") long id) {

		try {
			return gameService.editGame(gameService.findById(id).get());
		} catch (GameException | CapacityException | SpringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@GetMapping("/games")
	public Iterable<TsscGame> findAll() {
		return gameService.findAll();

	}

	@GetMapping("/games/{id}")
	public TsscGame findById(@PathVariable("id") long id) {
		return gameService.findById(id).get();
	}

	@DeleteMapping("/games/{id}")
	public void deleteGame(@PathVariable("id") long id) {
		gameService.delete(gameService.findById(id).get());
	}

}
