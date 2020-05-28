package co.edu.icesi.ci.mvctutorial.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.mvctutorial.model.User;
import co.edu.icesi.ci.mvctutorial.service.UserServiceInt;

@Controller
public class UserController {

	UserServiceInt userService;

	@Autowired
	public UserController(UserServiceInt userService) {
		this.userService = userService;
		;
	}

	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@GetMapping("/users/add")
	public String addUser(Model model, @ModelAttribute("user") User user) {
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());
		return "users/add-user";
	}

	@PostMapping("/users/add")
	public String saveUser(User user, @RequestParam(value = "action", required = true) String action) {
	 if (!action.equals("Cancel"))
		userService.save(user);
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
		return "users/edit-user";
	}

	@PostMapping("/users/edit/{id}")
	public String updateUser(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, User user, Model model) {
		if (action != null && !action.equals("Cancel")) {
			userService.save(user);
		}
		return "redirect:/users/";
	}

	@GetMapping("/users/del/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userService.delete(user);
		return "redirect:/users/";
	}
}
