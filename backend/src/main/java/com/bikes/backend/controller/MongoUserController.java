package com.bikes.backend.controller;

import com.bikes.backend.model.MongoUser;
import com.bikes.backend.repository.MongoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MongoUserController {
    private final MongoUserRepository mongoUserRepository;

    @PostMapping
    public MongoUser create (@RequestBody MongoUser user) {
        return mongoUserRepository.save(user);
    }
}
