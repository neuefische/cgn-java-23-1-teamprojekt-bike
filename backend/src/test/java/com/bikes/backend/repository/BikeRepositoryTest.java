package com.bikes.backend.repository;

import com.bikes.backend.model.Bike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BikeRepositoryTest {
    Bike testBike;
    BikeRepository nonEmptyRepo;
    BikeRepository testRepoEmpty;

    @BeforeEach
    public void setUp() {
        testBike = new Bike("testBike", "testId");
        nonEmptyRepo = new BikeRepository(Map.of(testBike.id(), testBike));
        testRepoEmpty = new BikeRepository(new HashMap<>());
    }
    @Test
    void getAllBikesEmptyRepo() {
        //GIVEN
        List<Bike> expectedBikes  = new ArrayList<>();
        //WHEN
        List<Bike> actualBikes  = testRepoEmpty.getAllBikes();
        //THEN
        assertArrayEquals(expectedBikes, actualBikes);
    }

    @Test
    void getAllBikesNonEmptyRepo() {
        //GIVEN
        List<Bike> expectedBikes  = List.of(testBike);
        //WHEN
        List<Bike> actualBikes  = nonEmptyRepo.getAllBikes();
        //THEN
        assertEquals(expectedBikes, actualBikes);
    }

}