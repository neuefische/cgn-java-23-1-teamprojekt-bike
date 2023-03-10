package com.bikes.backend.controller;

import com.bikes.backend.model.MongoUser;
import com.bikes.backend.repository.MongoUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class MongoUserControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	MongoUserRepository mongoUserRepository;
	MongoUser basicUser = new MongoUser("Some test ID", "Test user", "Test password", "BASIC");
	Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

	@Nested
	@DisplayName("POST /api/users/")
	class testCreateUser {
		@Test
		@DirtiesContext
		@DisplayName("...should return 'Created' (201) if the user is successfully created")
		void create_returns201IfUserIsSuccessfullyCreated() throws Exception {
			//GIVEN
			//WHEN
			mockMvc.perform(post("/api/users/")
							.contentType(MediaType.APPLICATION_JSON)
							.content("""
									{
										"username": "Test user",
										"password": "Test password"
									}
									""").with(csrf()))
					.andExpect(status().isCreated());
			//THEN
		}

		@Test
		@DirtiesContext
		@DisplayName("...should return 'Conflict' (409) if the user already exists")
		void create_returns409IfUserAlreadyExists() throws Exception {
			//GIVEN
			mongoUserRepository.save(basicUser);
			//WHEN
			mockMvc.perform(post("/api/users/")
							.contentType(MediaType.APPLICATION_JSON)
							.content("""
									{
										"username": "Test user",
										"password": "Test password"
									}
									""").with(csrf()))
					.andExpect(status().isConflict());
			//THEN
		}

		@Test
		@DirtiesContext
		@DisplayName("...should return 'Bad Request' (400) if the username or password is missing")
		void create_returns400IfUsernameOrPasswordIsMissing() throws Exception {
			//GIVEN
			//WHEN
			mockMvc.perform(post("/api/users/")
							.contentType(MediaType.APPLICATION_JSON)
							.content("""
									{
										"username": "Test user",
										"password": ""
									}
									""").with(csrf()))
					.andExpect(status().isBadRequest());
			//THEN
		}

	}

	@Nested
	@DisplayName("GET /api/users/me a.k.a. useAuth()-Endpoint")
	class testGetCurrentUser {

		@Test
		@DirtiesContext
		@DisplayName("...should return 'Unauthorized' (401) if the user is not authenticated")
		void getCurrentUser_returns401IfTheUserIsNotAuthenticated() throws Exception {
			mockMvc.perform(get("/api/users/me").with(csrf()))
					.andExpect(status().isUnauthorized());
		}

		@Test
		@DirtiesContext
		@WithMockUser(username = "Test user", roles = {"BASIC"})
		@DisplayName("...should return a user object without a password(200) if the user is authenticated")
		void getCurrentUser_returnsUserWithoutPasswordIfTheUserIsAuthenticated() throws Exception {
			//GIVEN
			mongoUserRepository.save(basicUser);
			//WHEN
			mockMvc.perform(get("/api/users/me").with(csrf()))
					.andExpect(status().isOk())
					.andExpect(content().json("""
							{
								"username": "Test user",
								"role": "BASIC"
							}
							"""))
					.andExpect(jsonPath("$.id").isNotEmpty())
					.andExpect(jsonPath("$.password").isEmpty());
		}
	}


	@Nested
	@DisplayName("POST /api/users/login")
	class testLogin {

		@Test
		@DirtiesContext
		@DisplayName("...should return 'Unauthorized' (401) if the user with respective password does not exist")
		void login_returns401IfUserWithRespectivePasswordDoesNotExist() throws Exception {
			//GIVEN
			//WHEN
			mockMvc.perform(post("/api/users/login")
							.with(httpBasic(basicUser.username(), basicUser.password()))
							.contentType(MediaType.APPLICATION_JSON)
							.content("{}")
							.with(csrf()))
					.andExpect(status().isUnauthorized());
			//THEN
		}

		@Test
		@DirtiesContext
		@DisplayName("...should return user object without password (200) if the user with respective password exists")
		void login_returnsUserWithoutPasswordIfUserWithRespectivePasswordExists() throws Exception {
			//GIVEN
			MongoUser expected = new MongoUser(basicUser.id(), basicUser.username(), encoder.encode(basicUser.password()), basicUser.role());
			mongoUserRepository.save(expected);
			//WHEN
			mockMvc.perform(post("/api/users/login")
							.with(httpBasic(basicUser.username(), basicUser.password()))
							.contentType(MediaType.APPLICATION_JSON)
							.content("{}")
							.with(csrf()))
					.andExpect(status().isOk())
					.andExpect(content().json("""
							{
								"username": "Test user",
								"role": "BASIC"
							}
							"""))
					.andExpect(jsonPath("$.id").isNotEmpty())
					.andExpect(jsonPath("$.password").isEmpty());
			//THEN
		}

	}

}