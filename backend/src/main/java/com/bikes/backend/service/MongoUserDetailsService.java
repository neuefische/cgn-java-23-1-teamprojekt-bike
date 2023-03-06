package com.bikes.backend.service;

import com.bikes.backend.model.MongoUser;
import com.bikes.backend.repository.MongoUserRepository;
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
        MongoUser optionalMongoUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(optionalMongoUser.username(), optionalMongoUser.password(), List.of());
    }
}
