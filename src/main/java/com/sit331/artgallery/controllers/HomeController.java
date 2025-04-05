package com.sit331.artgallery.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
	
	@GetMapping("/api")
	public String index() {
		return "Hello World!";
	}
	

}
