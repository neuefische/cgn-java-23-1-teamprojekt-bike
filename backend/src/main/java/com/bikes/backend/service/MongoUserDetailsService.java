package com.bikes.backend.service;

import com.bikes.backend.model.MongoUser;
import com.bikes.backend.model.MongoUserDTO;
import com.bikes.backend.model.MongoUserResponseDTO;
import com.bikes.backend.repository.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoUserDetailsService implements UserDetailsService {
	private final MongoUserRepository repository;
	private final IdService idService;
	private final PasswordEncoder passwordEncoder;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MongoUser mongoUser = repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return new User(mongoUser.username(), mongoUser.password(),
				List.of(new SimpleGrantedAuthority("ROLE_" + mongoUser.role())));
	}


	public MongoUserResponseDTO createUser(MongoUserDTO user) {
		if (user.username() == null || user.username().length() == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");
		}
		if (user.password() == null || user.password().length() == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
		}
		if (repository.existsByUsername(user.username())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
		}


		MongoUser newUser = new MongoUser(idService.generateId(), user.username(),
				passwordEncoder.encode(user.password()), "BASIC");

		MongoUser userOut = repository.save(newUser);

		return new MongoUserResponseDTO(userOut.id(), userOut.username(), userOut.role());

	}

	public MongoUserResponseDTO getCurrentUser(Principal principal) {
		MongoUser user = repository.findByUsername(principal.getName())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

		return new MongoUserResponseDTO(user.id(),user.username(), user.role());
	}
}
