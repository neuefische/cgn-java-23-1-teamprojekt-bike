package com.bikes.backend.service;

import com.bikes.backend.model.MongoUser;
import com.bikes.backend.repository.MongoUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoUserDetailsService implements UserDetailsService {
	private final MongoUserRepository repository;

	public MongoUserDetailsService(MongoUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MongoUser mongoUser = repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return new User(mongoUser.username(), mongoUser.password(),
				List.of(new SimpleGrantedAuthority("ROLE_" + mongoUser.role())));
	}

	public MongoUser loadMongoUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}
