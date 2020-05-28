package co.edu.icesi.taller2.controller;

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

import co.edu.icesi.taller2.model.TsscTopic;
import co.edu.icesi.taller2.repository.TopicRepository;
import co.edu.icesi.taller2.service.TopicService;

@Controller
public class TopicController {
	
	private TopicService topicService;

	@Autowired
	public TopicController(TopicService topicService) {
		
		this.topicService = topicService;
	}
	
	@GetMapping("/topics/")
	public String indexTopic(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "topics/index";
	}
	
	@GetMapping("/topics/add")
	public String addTopic(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "topics/add-topic";
	}
	
	@PostMapping("/topics/add")
	public String saveTopic(@RequestParam(value = "action", required = true) String action, @Valid TsscTopic topic,
			BindingResult bindingResult, Model model)
	{
		if(!action.equals("Cancel"))
		{
			if(bindingResult.hasErrors())
			{
				return "topics/add-topic";
			}else
			{
				
				try {
					topicService.saveTopic(topic);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "redirect:/topics/";	
			}
		}else {
			return "topics/index";
		}
		
			
	}
	
	@GetMapping("/topics/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<TsscTopic> topic = topicService.findById(id);
		if (topic == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("tsscTopic", topic.get());
		return "topics/update-topic";
	}
	
	@PostMapping("/topics/edit/{id}")
	public String updateTopic(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Valid TsscTopic topic, BindingResult bindingResult) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "topics/update-topic";
			} else {
				try {
					topicService.editTopic(topic);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return "redirect:/topics/";
	}
	
	@GetMapping("/topics/del/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		TsscTopic user = topicService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		topicService.delete(user);
		return "redirect:/topics/";
	}
	


}
