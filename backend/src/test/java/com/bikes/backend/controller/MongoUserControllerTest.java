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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MongoUserControllerTest {


	@Autowired
	MockMvc mockMvc;
	@Autowired
	MongoUserRepository mongoUserRepository;
	MongoUser basicUser = new MongoUser("Some test ID", "Test user", "Test password", "BASIC");
	String invalidId = "Some invalid ID";

//	@Test
//	void getMe2() {
//	}

	@Nested
	@DisplayName("POST /api/users/")
	class testCreateUser {
		@Test
		@DisplayName("...should return a 201 if the user is successfully created")
		void create_returns201IfUserIsSuccessfullyCreated() throws Exception {
			//GIVEN
			//WHEN
			mockMvc.perform(post("/api/users/")
							.contentType(MediaType.APPLICATION_JSON)
							.contentType("""
									{
										"username": "Test user",
										"password": "Test password"
									}
									""").with(csrf()))
					.andExpect(status().isCreated());
			//THEN
		}

	}


	@Nested
	@DisplayName("GET /api/users/admin")
	class testGetAdmin {

		@Test
		@DisplayName("...should return 'Unauthorized' (401) if the user is not authenticated")
		void getAdmin_returns401IfTheUserIsNotAuthenticated() throws Exception {
			mockMvc.perform(get("/api/users/admin").with(csrf()))
					.andExpect(status().isUnauthorized());
		}

		@Test
		@WithMockUser(roles = {"BASIC"})
		@DisplayName("...should return 'Forbidden' (403) if the user is not an admin")
		void getAdmin_returns403IfTheUserIsNotAnAdmin() throws Exception {
			mockMvc.perform(get("/api/users/admin").with(csrf()))
					.andExpect(status().isForbidden());
		}

		@Test
		@WithMockUser(roles = {"ADMIN"})
		@DisplayName("...should return the greeting for the admin user if the user is an admin")
		void getAdmin_returnsGreetingForAdminUser() throws Exception {
			mockMvc.perform(get("/api/users/admin").with(csrf()))
					.andExpect(status().isOk())
					.andExpect(content().string("Hello, Admin!"));
		}


	}
}