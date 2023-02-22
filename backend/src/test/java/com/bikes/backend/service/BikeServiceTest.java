package com.bikes.backend.service;

import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@Service
class BikeServiceTest {

    BikeRepository bikeRepository = mock(BikeRepository.class);
    BikeService bikeService = new BikeService(bikeRepository);

    Bike testBike = new Bike("testBike", "testId");
    Bike[] expectedBikes = {testBike};
    Bike[] expectedBikesEmpty = {};

    @Test
    void getAllBikesNonEmptyRepo() {
        //GIVEN
        when(bikeRepository.getAllBikes()).thenReturn(expectedBikes);
        //WHEN
        Bike[] actual = bikeService.getAllBikes();
        //THEN
        verify(bikeRepository).getAllBikes();
        Assertions.assertEquals(expectedBikes, actual);
    }

    @Test
    void getAllBikesEmptyRepo() {
        //GIVEN
        when(bikeRepository.getAllBikes()).thenReturn(expectedBikesEmpty);
        //WHEN
        Bike[] actual = bikeService.getAllBikes();
        //THEN
        verify(bikeRepository).getAllBikes();
        Assertions.assertEquals(expectedBikesEmpty, actual);
    }
}