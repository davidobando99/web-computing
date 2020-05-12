package co.edu.icesi.fi.tics.tssc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

}
