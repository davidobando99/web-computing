package co.edu.icesi.fi.tics.tssc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.services.GameService;
import co.edu.icesi.fi.tics.tssc.services.StoryService;

@RestController
public class StoryRestController {

	private StoryService storyService;

	private GameService gameService;

	@Autowired
	public StoryRestController(StoryService storyService, GameService gameService) {
		// TODO Auto-generated constructor stub
		this.gameService = gameService;
		this.storyService = storyService;
	}

	@PostMapping("/api/stories/")
	public TsscStory saveStory(TsscStory nuevo) {

		try {
			gameService.findById(nuevo.getTsscGame().getId()).get().getTsscStories().add(nuevo);
			return storyService.saveStory(nuevo, nuevo.getTsscGame().getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@PostMapping("/api/stories/{id}")
	public TsscStory editStory(@PathVariable("id") long id) {

		TsscStory edited = storyService.findById(id).get();

		try {
			return storyService.editStory(edited);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	@GetMapping("/api/stories/")
	public Iterable<TsscStory> findAll(){		
		return storyService.findAll();		
	}
	
	@GetMapping("/stories/{id}")
	public TsscStory  findById(@PathVariable("id") long id) {
		return storyService.findById(id).get();
	}
	
	@DeleteMapping("/api/stories/{id}")
	public void deleteStory(@PathVariable("id") long id) {
		storyService.delete(storyService.findById(id).get());
		
	}

}
