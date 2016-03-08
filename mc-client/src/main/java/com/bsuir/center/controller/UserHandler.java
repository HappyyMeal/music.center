package com.bsuir.center.controller;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.bsuir.center.domain.User;
import com.bsuir.center.service.IUserService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/user")
@SessionAttributes("username")
public class UserHandler {

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/login", method = POST)
	public String login(@RequestParam String username, @RequestParam String password, Model model) {

		User user = null;
		if (restTemplate != null && environment != null)
			user = restTemplate.getForObject(environment.getProperty("USER_REST_SERVICE_URI") + "/" + username,
					User.class);

		if (user != null && password.equals(user.getUserPassword())) {
			model.addAttribute("username", username);
			return "home";
		}

		model.addAttribute("loginFailed", true);
		return "login";

	}

	@RequestMapping(value = "/registration", method = GET)
	public String initRegistrationPage() {
		return "registration";
	}

	@RequestMapping(value = "/login", method = GET)
	public String initLoginPage(Model model) {
		model.addAttribute("loginFailed", false);
		return "login";
	}

}
