package com.bikes.backend.controller;

import com.bikes.backend.model.MongoUser;
import com.bikes.backend.repository.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MongoUserController {
    private final MongoUserRepository mongoUserRepository;

    @PostMapping
    public MongoUser create (@RequestBody MongoUser user) {
        return mongoUserRepository.save(user);
    }

    @GetMapping("/me")
    public String getMe2() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}