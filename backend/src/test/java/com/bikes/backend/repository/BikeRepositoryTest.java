package com.bikes.backend.repository;

import com.bikes.backend.model.Bike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
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
        Bike[] expectedBikes  = {};
        //WHEN
        Bike[] actualBikes  = testRepoEmpty.getAllBikes();
        //THEN
        assertArrayEquals(expectedBikes, actualBikes);
    }

    @Test
    void getAllBikesNonEmptyRepo() {
        //GIVEN
        Bike[] expectedBikes  = {testBike};
        //WHEN
        Bike[] actualBikes  = nonEmptyRepo.getAllBikes();
        //THEN
        assertArrayEquals(expectedBikes, actualBikes);
    }

}