package co.edu.icesi.fi.tics.tssc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.exceptions.CapacityException;
import co.edu.icesi.fi.tics.tssc.exceptions.SpringException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicException;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.services.GameService;
import co.edu.icesi.fi.tics.tssc.services.StoryService;
import co.icesi.fi.tics.tssc.validations.ValidationStory;
import co.icesi.fi.tics.tssc.validations.ValidationTopic;

@Controller
public class StoryController {

	private StoryService storyService;

	private GameService gameService;

	@Autowired
	public StoryController(StoryService storyService, GameService gameService) {
		this.storyService = storyService;
		this.gameService = gameService;
	}

	@GetMapping("/story/")
	public String indexStory(Model model) {
		model.addAttribute("stories", storyService.findAll());
		return "story/index";
	}

	@GetMapping("/story/add")
	public String addStory(Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("games", gameService.findAll());
		return "story/add-story";
	}

	@PostMapping("/story/add")
	public String saveStory(@Validated(ValidationStory.class) TsscStory tsscStory, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {

		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {

				model.addAttribute("description", tsscStory.getDescription());
				model.addAttribute("businessValue", tsscStory.getBusinessValue());
				model.addAttribute("initialSprint", tsscStory.getInitialSprint());
				model.addAttribute("priority", tsscStory.getPriority());
				model.addAttribute("games", gameService.findAll());

				return "story/add-story";
			} else {

				// Guarda una Historia con el juego obligatorio.
				try {
					
					gameService.findById(tsscStory.getTsscGame().getId()).get().getTsscStories().add(tsscStory);
									
					storyService.saveStory(tsscStory, tsscStory.getTsscGame().getId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return "redirect:/story/";
			}
		} else {

			model.addAttribute("stories", storyService.findAll());
			return "story/index";
		}

	}

	// ----Fin de guardar Story -----

	@GetMapping("/story/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<TsscStory> tsscStory = storyService.findById(id);

		if (tsscStory == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);

		model.addAttribute("tsscStory", tsscStory.get());
		model.addAttribute("description", tsscStory.get().getDescription());
		model.addAttribute("businessValue", tsscStory.get().getBusinessValue());
		model.addAttribute("initialSprint", tsscStory.get().getInitialSprint());
		model.addAttribute("priority", tsscStory.get().getPriority());
		model.addAttribute("games", gameService.findAll());

		return "story/update-story";
	}

	@PostMapping("story/edit/{id}")
	public String updateStory(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			@Validated(ValidationStory.class) TsscStory tsscStory, BindingResult bindingResult, Model model) {

		if (action.equals("Cancelar")) {

			return "redirect:/story/";
		}

		if (bindingResult.hasErrors()) {

			model.addAttribute("games", gameService.findAll());
			model.addAttribute("description", tsscStory.getDescription());
			model.addAttribute("businessValue", tsscStory.getBusinessValue());
			model.addAttribute("initialSprint", tsscStory.getInitialSprint());
			model.addAttribute("priority", tsscStory.getPriority());
			
			return "story/update-story";
		}

		if (action != null && !action.equals("Cancelar")) {

			try {
				storyService.saveStory(tsscStory, tsscStory.getTsscGame().getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "redirect:/story/";
	}

	@GetMapping("/story/del/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		TsscStory tsscStory = storyService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid story Id:" + id));
		storyService.delete(tsscStory);
		return "redirect:/story/";
	}

}
