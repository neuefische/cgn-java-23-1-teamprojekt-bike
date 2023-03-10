package com.bikes.backend.service;

import com.bikes.backend.model.MongoUser;
import com.bikes.backend.model.MongoUserDTO;
import com.bikes.backend.model.MongoUserResponseDTO;
import com.bikes.backend.repository.MongoUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class MongoUserDetailsServiceTest {
    @Autowired
    MongoUserRepository mongoUserRepository;
    @Autowired
    MongoUserDetailsService mongoUserDetailsService;
    IdService idService = mock(IdService.class);
    PasswordEncoder passwordEncoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    MongoUser mongoUser = new MongoUser("1", "name", "11", "BASIC");
    MongoUserDTO mongoUserDTO = new MongoUserDTO(mongoUser.username(), mongoUser.password());

    @BeforeEach
    void setUp() {
        mongoUserDetailsService = new MongoUserDetailsService(mongoUserRepository, idService, passwordEncoder);
    }
//    @Nested
//    class testLoadUserByUsername{
//        @Test
//        void whenUserFound_thenReturnUser() {
//            //GIVEN
//            mongoUserRepository.save(mongoUser);
//            User expected = new User(mongoUser.username(), mongoUser.password(),
//                    List.of(new SimpleGrantedAuthority("ROLE_" + mongoUser.role())));
//            //WHEN
//            UserDetails actual = mongoUserDetailsService.loadUserByUsername("name");
//            //THEN
//            assertEquals(expected, actual);
//
//    }
//    }
    @Nested
       class testLoadUserByUsername {

    @Test
    void createUser() {
        //GIVEN
        MongoUserResponseDTO expected = new MongoUserResponseDTO(mongoUser.id(), mongoUser.username(), mongoUser.role());
        //WHEN
        MongoUserResponseDTO actual = mongoUserDetailsService.createUser(mongoUserDTO);
        //THEN
        assertEquals(expected, actual);
    }
}
    @Test
    void getCurrentUser() {
    }
}