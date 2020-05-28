package co.edu.icesi.ci.mvctutorial.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.View;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.mvctutorial.model.User;
import co.edu.icesi.ci.mvctutorial.model.UserGender;
import co.edu.icesi.ci.mvctutorial.model.UserType;
import co.edu.icesi.ci.mvctutorial.service.AppointmentService;
import co.edu.icesi.ci.mvctutorial.service.UserService;

@Controller
public class UserController {
	
	
	private UserService userService;
	
	public UserController(UserService userService) {
		
		this.userService=userService;
	}
	
	@GetMapping("/users/")
	public String getUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}
	
	@GetMapping("/users/add")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());
		return "users/add-user";
	}

	@PostMapping("/users/add")
	public String addUser(User user,Model model) {
		userService.save(user);
		//Otra forma de agregar al usuario sin pedir el user por parametro
//		Map<String,Object> m = model.asMap();
//		User u = (User) m.get("user");
//		userService.save(u);
		
		return "redirect:/users/";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(Model model, @PathVariable long id) {
		model.addAttribute("user", userService.findById(id).get());
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());
		return "users/edit-user";
	}
	@PostMapping("/users/edit")
	public String editUser(User user) {
		userService.save(user);
		
		return "redirect:/users/";
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		User user = userService.findById(id).get();
		userService.delete(user);
		return "redirect:/users/";
	}
	
	
	

}
