package com.springworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String getMessage() {
		return "Welcome to spring boot application for external tomcat deployment!";
	}
}
