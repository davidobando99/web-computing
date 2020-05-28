package co.edu.icesi.ci.thymeval.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.thymeval.model.User;
import co.edu.icesi.ci.thymeval.model.User.validator1;
import co.edu.icesi.ci.thymeval.model.User.validator2;
import co.edu.icesi.ci.thymeval.service.UserService;

@Controller
public class UserController {

	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
		;
	}

	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}


	@GetMapping("/users/add")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		
		return "users/add-user-1";
	}
	
	@PostMapping("/users/add")
	public String saveUser(@Validated(validator1.class)User user,BindingResult bindingResult,  @RequestParam(value = "action", required = true) String action, Model model) {
		if (bindingResult.hasErrors()) {
			 return "users/add-user-1";
		 }
		
		if (!action.equals("Cancel")) {
				
			model.addAttribute("id", user.getId());
			model.addAttribute("genders", userService.getGenders());
			model.addAttribute("types", userService.getTypes());
			userService.save(user);
		}
				
		return "users/add-user-2";
		
	}
	
	
	@PostMapping("/users/add2/")
	public String saveUser2(@Validated(validator2.class)User user,BindingResult bindingResult, @RequestParam(value = "action", required = true) String action) {
		if (bindingResult.hasErrors()) {
			 return "users/add-user-2";
		 }
		
		if (!action.equals("Cancel")) {
			User user2 = userService.findById(user.getId()).get();
			user2.setName(user.getName());
			user2.setGender(user.getGender());
			user2.setType(user.getType());
			user2.setEmail(user.getEmail());
			userService.save(user);
				
		}
				
				
		return "redirect:/users/";
		
		
	}

	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<User> user = userService.findById(id);
		if (user == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("user", user.get());
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());
		return "users/update-user";
	}

	@PostMapping("/users/edit/{id}")
	public String updateUser(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,@Validated User user,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			 return "users/update-user";
		 }
		if (action != null && !action.equals("Cancel")) {
			userService.save(user);
		}
		return "redirect:/users/";
	}

	@GetMapping("/users/del/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userService.delete(user);
		return "redirect:/users/";
	}
}
