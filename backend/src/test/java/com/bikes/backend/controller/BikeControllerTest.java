package com.bikes.backend.controller;

import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BikeControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	BikeRepository bikeRepository;
	Bike testBike = new Bike("Some test ID", "Mega bike 9000", null);
	String invalidId = "Some invalid ID";

	@Nested
	@DisplayName("GET All /api/bikes")
	class testGetAllBikes {

		@Test
		@DisplayName("...should return 'Unauthorized' (401) if the user is not logged in")
		void testGetAllBikes_unauthorized() throws Exception {
			mockMvc.perform(get("/api/bikes/"))
					.andExpect(status().isUnauthorized());
		}

		@Test
		@WithMockUser
		@DisplayName("...should return an empty array if there are no bikes in the database and the user is logged in")
		void testGetAllBikes_emptyArray() throws Exception {
			mockMvc.perform(get("/api/bikes/"))
					.andExpect(status().isOk())
					.andExpect(content().json("[]"));
		}

	}


	@Nested
	@DisplayName("GET /api/bikes/{id}")
	class testGetBikeById {

		@Test
		@DisplayName("...should return 'Unauthorized' (401) if the user is not logged in")
		void testGetBikeById_unauthorized() throws Exception {
			mockMvc.perform(get("/api/bikes/" + testBike.id()))
					.andExpect(status().isUnauthorized());
		}

		@Test
		@WithMockUser
		@DisplayName("...should throw an exception if the user is logged in but the bike with the given id does not exist in the database")
		void testGetBikeById_throwsException() throws Exception {
			//WHEN + THEN
			mockMvc.perform(get("/api/bikes/" + invalidId))
					.andExpect(status().isNotFound());
		}

		@Test
		@WithMockUser
		@DirtiesContext
		@DisplayName("...should return a bike if the bike with the given id does exist and the user is logged in")
		void testGetBikeByIdExists() throws Exception {
			//GIVEN
			bikeRepository.save(testBike);

			//WHEN + THEN
			mockMvc.perform(get("/api/bikes/" + testBike.id()))
					.andExpect(status().isOk())
					.andExpect(content().json("""
							        {
							            "id": "Some test ID",
							            "title": "Mega bike 9000"
							        }
							"""));
		}


	}


	@Nested
	@DisplayName("POST /api/bikes")
	class testPostBike {

		@Test
		@DisplayName("...should return 'Unauthorized' (401) if the user is not logged in")
		void addBike_returns403IfTheUserIsNotLoggedIn() throws Exception {
			mockMvc.perform(post("/api/bikes/")
					.contentType(MediaType.APPLICATION_JSON)
					.content("""
							        {
							        "title": "Mega bike 9000"
							        }
							""")
					.with(csrf())).andExpect(status().isUnauthorized());
		}

		@Test
		@DirtiesContext
		@WithMockUser()
		@DisplayName("...should add a bike to the database and return it if the user is logged in")
		void addBike_returnsABike() throws Exception {
			//WHEN + THEN
			mockMvc.perform(post("/api/bikes/")
							.contentType(MediaType.APPLICATION_JSON)
							.content("""
									        {
									        "title": "Mega bike 9000"
									        }
									""")
							.with(csrf()))
					.andExpect(status().isOk())
					.andExpect(content().json("""
							        {
							        "title": "Mega bike 9000"
							        }
							"""))
					.andExpect(jsonPath("$.id").isNotEmpty());
		}

	}

	@Nested
	@DisplayName("PUT /api/bikes")
	class testPutBike {

		@Test
		@DisplayName("...should return 'Unauthorized' (401) if the user is not logged in")
		void updateBike_returns403IfTheUserIsNotLoggedIn() throws Exception {
			mockMvc.perform(put("/api/bikes/")
					.contentType(MediaType.APPLICATION_JSON)
					.content("""
							        {
							        "id": "Some test ID",
							        "title": "Mega bike 9000 ver.2"
							        }
							""").with(csrf())).andExpect(status().isUnauthorized());
		}

		@Test
		@DirtiesContext
		@WithMockUser()
		@DisplayName("...should update the bike and return it if there is a bike with the given id in the database and the user is logged in")
		void updateBike_returnsABikeIfThereIsABikeWithTheGivenId() throws Exception {
			//GIVEN
			bikeRepository.save(testBike);

			//WHEN + THEN
			mockMvc.perform(put("/api/bikes/")
							.contentType(MediaType.APPLICATION_JSON)
							.content("""
									        {
									        "id": "Some test ID",
									        "title": "Mega bike 9000 ver.2"
									        }
									""").with(csrf()))
					.andExpect(status().isOk())
					.andExpect(content().json("""
							        {
							        "id": "Some test ID",
							        "title": "Mega bike 9000 ver.2"
							        }
							"""));
		}

		@Test
		@DisplayName("...should throw an exception if there is no bike with the given id in the database and the user is logged in")
		@WithMockUser(username = "user", password = "123")
		void updateBike_throwsExceptionIfThereIsNoBikeWithTheGivenId() throws Exception {
			//WHEN + THEN
			mockMvc.perform(put("/api/bikes/")
					.contentType(MediaType.APPLICATION_JSON)
					.content("""
							        {
							        "id": "Some invalid ID",
							        "title": "Mega bike 9000 ver.2"
							        }
							""").with(csrf())).andExpect(status().isNotFound());
		}

	}

	@Nested
	@DisplayName("DELETE /api/bikes/{id}")
	class testDeleteBike {

		@Test
		@DisplayName("...should return 'Unauthorized' (401) if the user is not logged in")
		void deleteBike_returns403IfTheUserIsNotLoggedIn() throws Exception {
			mockMvc.perform(delete("/api/bikes/41").with(csrf()))
					.andExpect(status().isUnauthorized());
		}

		@Test
		@DirtiesContext
		@WithMockUser
		@DisplayName("...should delete the bike with the given id if it does exist in the database and the user is logged in")
		void deleteBike_deletesABikeIfTheBikeWithTheGivenIdDoesExist() throws Exception {
			bikeRepository.save(testBike);
			mockMvc.perform(delete("/api/bikes/" + testBike.id()).with(csrf()))
					.andExpect(status().isOk())
					.andExpect(content().json("""
							        {  
							           "title": "Mega bike 9000",
							           "id": "Some test ID"
							        }
							"""));
		}

		@Test
		@DirtiesContext
		@WithMockUser()
		@DisplayName("...should throw an exception if the bike with the given id does not exist but the user is logged in")
		void deleteBike_throwsExceptionIfTheBikeWithTheGivenIdDoesNotExist() throws Exception {
			mockMvc.perform(delete("/api/bikes/41").with(csrf()))
					.andExpect(status().isNotFound());
		}

	}

}
