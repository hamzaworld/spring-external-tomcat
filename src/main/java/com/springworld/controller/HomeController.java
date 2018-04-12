package com.springworld.controller;

import javax.annotation.Generated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String getMessage() {
		return "Welcome to spring boot application for external tomcat deployment!";
	}
	
	@GetMapping("/home")
	public String home() {
		return "This is home for demo!!";
	}
}
