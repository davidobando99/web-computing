package co.edu.icesi.fi.tics.tssc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.exceptions.CapacityException;
import co.edu.icesi.fi.tics.tssc.exceptions.SpringException;
import co.edu.icesi.fi.tics.tssc.exceptions.TopicException;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.services.TopicService;

@RestController
public class TopicRestController {

	private TopicService topicService;

	@Autowired
	public TopicRestController(TopicService topicService) {
		this.topicService = topicService;

	}

	@RequestMapping(value="/api/topics", method = RequestMethod.POST)
	public TsscTopic saveTopic(TsscTopic nuevo) {

		try {

			return topicService.saveTopic(nuevo);

		} catch (TopicException | CapacityException | SpringException e) {

		}

		return null;
	}
	
	@PostMapping("/api/topics/{id}")
	public TsscTopic editTopic(@PathVariable("id") long id) {
		
		try {
			TsscTopic editado = topicService.findById(id).get();
			return topicService.editTopic(editado);
		} catch (TopicException | CapacityException | SpringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GetMapping("api/topics")
	public Iterable<TsscTopic> findAll(){
		return topicService.findAll();		
	}
	
	@GetMapping("api/topics/{id}")
	public TsscTopic findById(@PathVariable("id") long id) {
		return topicService.findById(id).get();		
	}
	
	@DeleteMapping("/api/topics/{id}")
	public void deleteTopic(@PathVariable("id") long id) {
		
		TsscTopic del = topicService.findById(id).get();
		topicService.delete(del);
	}

	
	
}
