package com.springworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@GetMapping("/message")
	public String demoMessage() {
		return "This is a sample rest end point";
	}
	
	@GetMapping("/greeting")
	public String getGreeting() {
		return "This is a greeting via demo rest end point";
	}
}
