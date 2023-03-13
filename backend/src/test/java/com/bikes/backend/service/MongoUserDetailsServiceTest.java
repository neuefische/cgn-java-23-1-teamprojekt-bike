package com.bikes.backend.service;

import com.bikes.backend.model.MongoUser;
import com.bikes.backend.model.MongoUserRequest;
import com.bikes.backend.model.MongoUserResponse;
import com.bikes.backend.repository.MongoUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class MongoUserDetailsServiceTest {
	@Autowired
	MongoUserRepository mongoUserRepository;
	@Autowired
	MongoUserDetailsService mongoUserDetailsService;
	IdService idService = mock(IdService.class);
	PasswordEncoder passwordEncoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
	Principal principal = mock(Principal.class);
	MongoUser mongoUser = new MongoUser("1", "name", "11", "BASIC");
	MongoUserRequest mongoUserRequest = new MongoUserRequest(mongoUser.username(), mongoUser.password());
	MongoUserResponse mongoUserResponse = new MongoUserResponse(mongoUser.id(), mongoUser.username(), mongoUser.role());

	@BeforeEach
	void setUp() {
		mongoUserDetailsService = new MongoUserDetailsService(mongoUserRepository, idService, passwordEncoder);
		mongoUserRepository.findByUsername(mongoUser.username()).ifPresent(mongoUserRepository::delete);
		when(idService.generateId()).thenReturn(mongoUser.id());
		when(principal.getName()).thenReturn(mongoUser.username());
	}

	@Nested
	@DisplayName("getCurrentUser()")
	class testGetCurrentUser {
		@Test
		@DisplayName("...should return a MongoUserResponseDTO of the current user if user is logged in")
		void getCurrentUser_shouldReturnMongoUserResponseDTO() {
			//GIVEN
			mongoUserRepository.save(mongoUser);
			MongoUserResponse expected = mongoUserResponse;
			//WHEN
			MongoUserResponse actual = mongoUserDetailsService.getCurrentUser(principal);
			//THEN
			assertEquals(expected, actual);
		}

		@Test
		@DisplayName("...should throw 'Unauthorized' (401) if current user does not exist (the user is not logged in)")
		void getCurrentUser_shouldThrowUnauthorizedIfUserDoesNotExist() {
			//GIVEN
			ResponseStatusException expected = new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
			//WHEN
			ResponseStatusException actual = assertThrows(ResponseStatusException.class, () -> mongoUserDetailsService.getCurrentUser(principal));
			//THEN
			assertEquals(expected.getClass(), actual.getClass());
			assertEquals(expected.getMessage(), actual.getMessage());
		}
	}

	@Nested
	@DisplayName("loadUserByUsername()")
	class testLoadUserByUsername {
		@Test
		@DirtiesContext
		@DisplayName("...should return a MongoUserResponseDTO of the user with the given username if user exists")
		void loadUserByUsername_shouldReturnMongoUserResponseDTOIfUserExists() {
			//GIVEN
			mongoUserRepository.save(mongoUser);
			GrantedAuthority grantedAuthority = () -> "ROLE_" + mongoUser.role();
			Collection<GrantedAuthority> mongoUserAuthorities = new ArrayList<>(Arrays.asList(grantedAuthority));
			UserDetails expected = new User(mongoUser.username(), mongoUser.password(), mongoUserAuthorities);
			//WHEN
			UserDetails actual = mongoUserDetailsService.loadUserByUsername(mongoUser.username());
			//THEN
			assertEquals(expected, actual);
		}

		@Test
		@DisplayName("...should throw UsernameNotFoundException if user with given username does not exist")
		void loadUserByUsername_shouldThrowUsernameNotFoundExceptionIfUserDoesNotExist() {
			//GIVEN
			UsernameNotFoundException expected = new UsernameNotFoundException("User not found");
			//WHEN
			UsernameNotFoundException actual = assertThrows(expected.getClass(), () -> mongoUserDetailsService.loadUserByUsername(mongoUser.username()));
			//THEN
			assertEquals(expected.getClass(), actual.getClass());
			assertEquals(expected.getMessage(), actual.getMessage());
		}

	}

	@Nested
	@DisplayName("createUser()")
	class testCreateUser {

		@Test
		@DirtiesContext
		@DisplayName("...should return a MongoUserResponseDTO of the created user if username does not exist and provided username amd password are not empty")
		void createUser_shouldReturnMongoUserResponseDTOIfUsernameDoesNotExistAndUsernameAndPasswordAreNotEmpty() {
			//GIVEN
			MongoUserResponse expected = mongoUserResponse;
			//WHEN
			MongoUserResponse actual = mongoUserDetailsService.createUser(mongoUserRequest);
			//THEN
			assertEquals(expected, actual);
		}

		@Test
		@DisplayName("...should throw a ResponseStatusException with status 409 when username already exists")
		void createUser_shouldThrowResponseStatusException409IfUsernameAlreadyExists() {
			//GIVEN
			mongoUserRepository.save(mongoUser);
			HttpStatus expected = HttpStatus.CONFLICT;
			//WHEN
			ResponseStatusException actual = assertThrows(ResponseStatusException.class, () -> mongoUserDetailsService.createUser(mongoUserRequest));
			//THEN
			assertEquals(expected, actual.getStatusCode());
		}

		@Test
		@DisplayName("...should throw a ResponseStatusException with status 400 when username is empty")
		void createUser_shouldThrowResponseStatusException400IfUsernameIsEmpty() {
			//GIVEN
			MongoUserRequest mongoUserRequest = new MongoUserRequest("", mongoUser.password());
			HttpStatus expected = HttpStatus.BAD_REQUEST;
			//WHEN
			ResponseStatusException actual = assertThrows(ResponseStatusException.class, () -> mongoUserDetailsService.createUser(mongoUserRequest));
			//THEN
			assertEquals(expected, actual.getStatusCode());
		}

		@Test
		@DisplayName("...should throw a ResponseStatusException with status 400 when password is empty")
		void createUser_shouldThrowResponseStatusException400IfPasswordIsEmpty() {
			//GIVEN
			MongoUserRequest mongoUserRequest = new MongoUserRequest(mongoUser.username(), "");
			HttpStatus expected = HttpStatus.BAD_REQUEST;
			//WHEN
			ResponseStatusException actual = assertThrows(ResponseStatusException.class, () -> mongoUserDetailsService.createUser(mongoUserRequest));
			//THEN
			assertEquals(expected, actual.getStatusCode());
		}
	}

}
