package com.bikes.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/csrf")
public class CsrfController {
	@GetMapping
	public String getCsrf() {
		return "CSRF Ok";
	}
}
