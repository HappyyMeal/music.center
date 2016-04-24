package com.bsuir.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenericHandler {

	@RequestMapping(value = "/home")
	public String redirectToHome() {
		return "home";
	}

	@RequestMapping(value = "/error")
	public String redirectToError() {
		return "error";
	}

	@RequestMapping(value = "/search")
	public String redirectToSearch() {
		return "serach";
	}
}
