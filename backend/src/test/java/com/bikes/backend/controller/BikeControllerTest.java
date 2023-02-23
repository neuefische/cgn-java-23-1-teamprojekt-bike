package com.bikes.backend.controller;

import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import com.bikes.backend.service.NoSuchBikeException;
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


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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


    @Nested
    @DisplayName("GET /api/bikes/{id}")
    class testGetBikeById {

        private final String someIdThatDoesNotExist = "666";

        @Test
        @DirtiesContext
        @DisplayName("...should return a bike if the bike with the given id does exist")
        void testGetBikeById_returnsABikeIfThereIsABikeWithTheGivenId() throws Exception {
            bikeRepository.addBike(testBike);

            //when
            mockMvc.perform(MockMvcRequestBuilders.get("/api/bikes/" + testBike.id()))
                    .andExpect(status().isOk())
                    .andExpect(content().json(""" 
                                            {
                                                "title": "testBike"
                                            }
                                    """));
        }

        @Test
        @DisplayName("...should throw an exception if the bike with the given id does not exist")
        void testGetBikeById_throwsException() throws Exception {
            //when
            mockMvc.perform(MockMvcRequestBuilders.get("/api/bikes/1"))
                    .andExpect(status().isNotFound());
        }
    }



    @Nested
    @DisplayName("POST /api/bikes")
    class testPostBike {
        
    
        @Test
        @DirtiesContext
        @DisplayName("...should return a bike if there is a bike with the given id in the database")
        void addBike_returnsABikeIfThereIsABikeWithTheGivenId() throws Exception {
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
                                            "title": "testBike"
                                            }
                                    """))
                    .andExpect(jsonPath("$.id").isNotEmpty());

    

        }

    }
}


