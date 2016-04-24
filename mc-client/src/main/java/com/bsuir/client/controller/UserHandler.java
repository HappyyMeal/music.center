package com.bsuir.client.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bsuir.center.domain.User;
import com.bsuir.client.exception.ServiceException;
import com.bsuir.client.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserHandler {

	@Autowired
	private Environment environment;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/login", method = POST)
	public String login(@RequestParam String username, @RequestParam String password, Model model, HttpServletRequest request) {
		User user = processReadUser(username, model);

		if (user != null && userService.isMatchable(password, user.getUserPassword())) {
			request.getSession().setAttribute("username", username);
			return "redirect:/home";
		} else {
			model.addAttribute("error", environment.getProperty("PASSWORDS_DO_NOT_MATCH"));
		}

		return "login";
	}

	@RequestMapping(value = "/registration", method = POST)
	public String registration(@RequestParam String username, @RequestParam String password, Model model) {
		
		User user = processReadUser(username, model);
		String createdUsername = null;
		try {
			if (user == null) {
				User uiUser = new User();
				uiUser.setUserLogin(username);
				uiUser.setUserPassword(password);

				createdUsername = userService.create(uiUser);

			} else {
				model.addAttribute("error", environment.getProperty("USER_ALREADY_EXIST"));
				return "registration";
			}

		} catch (ServiceException e) {
			model.addAttribute("error", environment.getProperty("DEFAULT_ERROR_MESSAGE"));
		}

		model.addAttribute("success", environment.getProperty("SUCCESS_REGISTRATION") + createdUsername);
		return "login";
	}

	@RequestMapping(value = "/registration", method = GET)
	public String initRegistrationPage(Model model) {
		model.addAttribute("error", null);
		return "registration";
	}

	@RequestMapping(value = "/login", method = GET)
	public String initLoginPage(@RequestParam(value = "success", required = false) String success, Model model) {
		model.addAttribute("error", null);
		model.addAttribute("success", null);

		if (success != null) {
			model.addAttribute("success", success);
		}
		return "login";
	}

	private User processReadUser(String username, Model model) {
		User user = null;

		try {
			user = userService.read(username);

		} catch (ServiceException e) {
			model.addAttribute("error", environment.getProperty("USERNAME_IS_EMPTY"));
		}

		return user;
	}
}
