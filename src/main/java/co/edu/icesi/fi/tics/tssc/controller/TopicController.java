package co.edu.icesi.fi.tics.tssc.controller;

import java.util.Optional;

import javax.validation.Valid;

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
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.services.TopicService;
import co.icesi.fi.tics.tssc.validations.ValidationTopic;

@Controller
public class TopicController {

	private TopicService topicService;

	@Autowired
	public TopicController(TopicService topicService) {
		this.topicService = topicService;
	}

	@GetMapping("/topic/")
	public String indexUser(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "topic/index";
	}

	@GetMapping("/topic/add")
	public String addTopic(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "topic/add-topic";
	}

	@PostMapping("/topic/add")
	public String saveTopic(@Validated(ValidationTopic.class) TsscTopic tsscTopic, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {

		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {

				model.addAttribute("name", tsscTopic.getName());
				model.addAttribute("description", tsscTopic.getDescription());
				model.addAttribute("defaultGroups", tsscTopic.getDefaultGroups());
				model.addAttribute("defaultSprints", tsscTopic.getDefaultSprints());
				model.addAttribute("groupPrefix", tsscTopic.getGroupPrefix());

				return "topic/add-topic";
			} else {

				try {
					topicService.saveTopic(tsscTopic);
				} catch (TopicException | CapacityException | SpringException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return "redirect:/topic/";

			}

		} else {

			model.addAttribute("topics", topicService.findAll());
			return "topic/index";
		}

	}

	// ----Fin de guardar tema -----

	@GetMapping("/topic/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<TsscTopic> tsscTopic = topicService.findById(id);

		if (tsscTopic == null)
			throw new IllegalArgumentException("Invalid topic Id:" + id);

		model.addAttribute("tsscTopic", tsscTopic.get());
		model.addAttribute("name", tsscTopic.get().getName());
		model.addAttribute("description", tsscTopic.get().getDescription());
		model.addAttribute("defaultGroups", tsscTopic.get().getDefaultGroups());
		model.addAttribute("defaultSprints", tsscTopic.get().getDefaultSprints());
		model.addAttribute("groupPrefix", tsscTopic.get().getGroupPrefix());

		return "topic/update-topic";
	}

	@PostMapping("/topic/edit/{id}")
	public String updateTopic(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			@Validated(ValidationTopic.class) TsscTopic tsscTopic, BindingResult bindingResult, Model model) {

		if (action.equals("Cancelar")) {

			return "redirect:/topic/";
		}

		if (bindingResult.hasErrors()) {
			
			model.addAttribute("name", tsscTopic.getName());
			model.addAttribute("description", tsscTopic.getDescription());
			model.addAttribute("defaultGroups", tsscTopic.getDefaultGroups());
			model.addAttribute("defaultSprints", tsscTopic.getDefaultSprints());
			model.addAttribute("groupPrefix", tsscTopic.getGroupPrefix());

			return "topic/update-topic";
		}

		if (action != null && !action.equals("Cancelar")) {
			try {
				topicService.saveTopic(tsscTopic);
			} catch (TopicException | CapacityException | SpringException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "redirect:/topic/";
	}

	@GetMapping("/topic/del/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		TsscTopic tsscTopic = topicService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		topicService.delete(tsscTopic);
		return "redirect:/topic/";
	}

}
