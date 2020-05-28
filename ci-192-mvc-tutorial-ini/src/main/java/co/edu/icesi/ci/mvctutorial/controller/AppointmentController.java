package co.edu.icesi.ci.mvctutorial.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.mvctutorial.model.Appointment;
import co.edu.icesi.ci.mvctutorial.model.User;
import co.edu.icesi.ci.mvctutorial.service.AppointmentService;
import co.edu.icesi.ci.mvctutorial.service.UserService;

@Controller
public class AppointmentController {
	
	
	private AppointmentService appService;

	private UserService userService;
	public AppointmentController(AppointmentService appService,UserService userService) {
		this.appService=appService;
		this.userService=userService;
	}
	
	@GetMapping("/apps/")
	public String getUsers(Model model) {
		model.addAttribute("app", appService.findAll());
		return "apps/index";
	}
	
	@GetMapping("/apps/add")
	public String addUser(Model model) {
		model.addAttribute("app", new Appointment());
		model.addAttribute("Patients", userService.findAllPatients());
		model.addAttribute("Doctors", userService.findAllDoctors());
		return "apps/add-app";
	}

	@PostMapping("/apps/add")
	public String addUser(Appointment app,Model model) {
		appService.save(app);
		//Otra forma de agregar al usuario sin pedir el user por parametro
//		Map<String,Object> m = model.asMap();
//		User u = (User) m.get("user");
//		userService.save(u);
		
		return "redirect:/apps/";
	}
	
	@GetMapping("/apps/edit/{id}")
	public String editUser(Model model, @PathVariable long id) {
		model.addAttribute("apps", appService.findById(id).get());
		
		return "apps/edit-app";
	}
	@PostMapping("/apps/edit")
	public String editUser(Appointment app) {
		appService.save(app);
		
		return "redirect:/apps/";
	}
	
	@GetMapping("/apps/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		Appointment appointment = appService.findById(id).get();
		appService.delete(appointment);
		return "redirect:/apps/";
	}
	
	

}
