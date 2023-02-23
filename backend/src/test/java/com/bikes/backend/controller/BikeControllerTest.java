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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BikeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BikeRepository bikeRepository;
    Bike testBike = new Bike("testId", "testBike");

    @Nested
    @DisplayName("GET /api/bikes")
    class testGetAllBikes {

        @Test
        @DisplayName("...should return an empty array if there are no bikes in the database")
        void testGetAllBikes_emptyArray() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/bikes"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[]"));
        }
    }

    @Test
    @DirtiesContext
    void addBike() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/bikes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
                                        {
                                        "id": "testId",
                                        "title": "testBike"
                                        }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json(""" 
                                        {
                                        "id": "testId",
                                        "title": "testBike"
                                        }
                                """));
    }

}
//
//	@Nested
//	@DisplayName("GET /api/bikes/{id}")
//	class testGetBikeById {
//		//Given
//		private final int someIdThatDoesNotExist = 666;
//
//		@Test
//		@DisplayName("...should throw an exception if the bike does not exist")
//		void testGetBikeById_throwsException() throws Exception {
//			//when
//			mockMvc.perform(MockMvcRequestBuilders.get("/api/bikes/" + someIdThatDoesNotExist))
//					.andExpect(status().isNotFound());
//
//		}
//	}
//


