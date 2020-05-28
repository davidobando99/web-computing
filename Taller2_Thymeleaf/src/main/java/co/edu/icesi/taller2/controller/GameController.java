package co.edu.icesi.taller2.controller;

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

import co.edu.icesi.taller2.model.TsscGame;
import co.edu.icesi.taller2.model.TsscTopic;
import co.edu.icesi.taller2.service.GameService;
import co.edu.icesi.taller2.service.TopicService;

@Controller
public class GameController {
	
	private GameService gameService;
	private TopicService topicService;
	@Autowired
	public GameController(GameService gameService, TopicService topicService) {
		this.gameService = gameService;
		this.topicService = topicService;
	}

	
	@GetMapping("/games/")
	public String indexGame(Model model) {
		model.addAttribute("games", gameService.findAll());
		
		return "games/index";
	}
	
	
	@GetMapping("/games/add")
	public String addGame(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		model.addAttribute("topics", topicService.findAll());

		return "games/add-game";
	}
	
	@PostMapping("/games/add")
	public String saveGame(@RequestParam(value = "action", required = true) String action,@Valid TsscGame game,
			BindingResult bindingResult, Model model)
	{
		if(!action.equals("Cancel"))
		{
			if(bindingResult.hasErrors())
			{
				model.addAttribute("topics", topicService.findAll());
				return "games/add-game";
			}else
			{
				//Guardar juego con tema seleccionado, puede ser null
				try {
					if(game.getTsscTopic()==null) {
						gameService.saveGame(game,null);
					}
					else {
					gameService.saveGame(game,game.getTsscTopic());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:/games/";	
			}
		}else {
			return "games/index";
		}
		
			
	}
	
	@GetMapping("/games/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<TsscGame> game = gameService.findById(id);
		if (game == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("tsscGame", game.get());
		model.addAttribute("topics", topicService.findAll());
		return "games/update-game";
	}
	
	@PostMapping("/games/edit/{id}")
	public String updateGame(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Valid TsscGame game, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("topics", topicService.findAll());
				return "games/update-game";
			} else {
				try {
					if(game.getTsscTopic()==null) {
						gameService.editGame(game,null);
						
					}
					else {
					gameService.editGame(game,game.getTsscTopic());
				
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return "redirect:/games/";
	}
	
	@GetMapping("/games/del/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		TsscGame game = gameService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		gameService.delete(game);
		return "redirect:/games/";
	}
	
	@GetMapping("/games/stories/{id}")
	public String showStories(@PathVariable("id") long id, Model model) {
		Optional<TsscGame> game = gameService.findById(id);
		if (game == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("tsscGame", game.get());
		model.addAttribute("stories", gameService.getStories(game.get()));
	
		return "games/get-stories";
	}
	
//	@PostMapping("/games/stories/{id}")
//	public String showStories(@PathVariable("id") long id,
//			@RequestParam(value = "action", required = true) String action, @Valid TsscGame game, BindingResult bindingResult, Model model) {
//		if (action != null && !action.equals("Cancel")) {
//			if (bindingResult.hasErrors()) {
//				
//				return "games/get-stories";
//			} else {
//				try {
//					model.addAttribute("stories", gameService.getStories(game));
//					System.out.println(gameService.getStories(game).get(0).getDescription());
//				
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//		}
//		return "redirect:/games/";
//	}
//	

}
