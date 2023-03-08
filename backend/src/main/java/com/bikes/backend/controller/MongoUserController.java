package com.bikes.backend.controller;

import com.bikes.backend.model.MongoUser;
import com.bikes.backend.model.MongoUserDTO;
import com.bikes.backend.repository.MongoUserRepository;
import com.bikes.backend.service.IdService;
import com.bikes.backend.service.MongoUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MongoUserController {
	private final MongoUserRepository mongoUserRepository;
	private final MongoUserDetailsService mongoUserDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final IdService idService;

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED, reason = "New user has been successfully created")
	public MongoUser create(@RequestBody MongoUserDTO user) {
		if (user.username() == null || user.username().length() == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");
		}
		if (user.password() == null || user.password().length() == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
		}
		if (mongoUserRepository.existsByUsername(user.username())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
		}

		MongoUser newUser = new MongoUser(idService.generateId(), user.username(), passwordEncoder.encode(user.password()), "BASIC");
		MongoUser userOut = mongoUserRepository.save(newUser);
		return new MongoUser(userOut.id(), userOut.username(), null, userOut.role());
	}

	@PostMapping("/login")
	public MongoUser login(Principal principal) {
		return getCurrentUser(principal);
	}

	@PostMapping("/logout")
	public void logout() {
	}

	@GetMapping("/me")
	public MongoUser getCurrentUser(Principal principal) {
		MongoUser currentUser = mongoUserDetailsService
				.loadMongoUserByUsername(
						principal.getName())
				.orElseThrow(() ->
						new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found")
				);
		return new MongoUser(currentUser.id(), currentUser.username(), null, currentUser.role());
	}

	@GetMapping("/admin")
	public String getAdmin() {
		return "Hello, Admin!";
	}

}
