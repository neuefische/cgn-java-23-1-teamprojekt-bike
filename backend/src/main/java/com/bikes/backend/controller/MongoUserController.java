package com.bikes.backend.controller;

import com.bikes.backend.model.MongoUserDTO;
import com.bikes.backend.model.MongoUserResponseDTO;
import com.bikes.backend.service.MongoUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MongoUserController {
	private final MongoUserDetailsService mongoUserDetailsService;

	@PostMapping("/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MongoUserResponseDTO create(@RequestBody MongoUserDTO user) {
		return mongoUserDetailsService.createUser(user);
	}

	@PostMapping("/login")
	public MongoUserResponseDTO login(Principal principal) {
		return getCurrentUser(principal);
	}

	@PostMapping("/logout")
	public void logout() {
		// logout is handled by Spring Security
	}

	@GetMapping("/me")
	public MongoUserResponseDTO getCurrentUser(Principal principal) {
		return mongoUserDetailsService.getCurrentUser(principal);
	}

}
