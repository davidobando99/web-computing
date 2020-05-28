package co.edu.icesi.taller3.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.taller3.model.TsscStory;
import co.edu.icesi.taller3.service.GameService;
import co.edu.icesi.taller3.service.StoryService;

@Controller
public class StoryController {
	
	private StoryService storyService;
	private GameService gameService;
	
	
	@Autowired
	public StoryController(StoryService storyService,GameService gameService) {
		this.storyService = storyService;
		this.gameService = gameService;
		
	}

	
	@GetMapping("/stories/")
	public String indexStory(Model model) {
		model.addAttribute("stories", storyService.findAll());
		
		return "stories/index";
	}
	
	
	@GetMapping("/stories/add")
	public String addStory(Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("games", gameService.findAll());

		return "stories/add-story";
	}
	
	@PostMapping("/stories/add")
	public String saveStory(@RequestParam(value = "action", required = true) String action,@Valid TsscStory story,
			BindingResult bindingResult, Model model)
	{
		if(!action.equals("Cancel"))
		{
			if(bindingResult.hasErrors())
			{
				model.addAttribute("games", gameService.findAll());
				return "stories/add-story";
			}else
			{
				
				try {
					
						storyService.saveStory(story,story.getTsscGame());
						System.out.println(story.getTsscGame().getName());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:/stories/";	
			}
		}else {
			return "stories/index";
		}
		
			
	}
	
	@GetMapping("/stories/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<TsscStory> story = storyService.findById(id);
		if (story == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("tsscStory", story.get());
		model.addAttribute("games", gameService.findAll());
		return "stories/update-story";
	}
	
	@PostMapping("/stories/edit/{id}")
	public String updateStory(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Valid TsscStory story, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("games", gameService.findAll());
				return "stories/update-story";
			} else {
				try {
				
					storyService.editStory(story,story.getTsscGame());
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return "redirect:/stories/";
	}
	
	@GetMapping("/stories/del/{id}")
	public String deleteStory(@PathVariable("id") long id) {
		TsscStory story = storyService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		storyService.delete(story);
		return "redirect:/stories/";
	}

}
