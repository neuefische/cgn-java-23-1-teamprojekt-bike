package com.bikes.backend.controller;

import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BikeControllerTest {

    @Autowired
    MockMvc mockMvc;


    BikeRepository emptyBikeRepository;
    Bike testBike = new Bike("testBike", "testId");


    BikeRepository nonEmptyBikeRepository = new BikeRepository(Map.of(testBike.id(), testBike));


    @Test
    void getAllBikes() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bikes"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
    @Test
    @DirtiesContext
    void getAllBikesNonEmptyRepo() throws Exception {
        //GIVEN
        System.out.println(nonEmptyBikeRepository.toString());
        //THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bikes"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                                       [{
                                                       "modelName": "testBike",
                                                       "id": "testId"
                                                        }]
                                                    """));
    }
}