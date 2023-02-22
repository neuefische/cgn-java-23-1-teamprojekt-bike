package com.bikes.backend.service;

import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@Service
class BikeServiceTest {

    BikeRepository bikeRepository = mock(BikeRepository.class);
    BikeService bikeService = new BikeService(bikeRepository);

    Bike testBike = new Bike("testBike", "testId");
    List<Bike> expectedBikes = List.of(testBike);
    List<Bike> expectedBikesEmpty = new ArrayList<>();

    @Test
    void getAllBikesNonEmptyRepo() {
        //GIVEN
        when(bikeRepository.getAllBikes()).thenReturn(expectedBikes);
        //WHEN
        List<Bike> actual = bikeService.getAllBikes();
        //THEN
        verify(bikeRepository).getAllBikes();
        Assertions.assertEquals(expectedBikes, actual);
    }

    @Test
    void getAllBikesEmptyRepo() {
        //GIVEN
        when(bikeRepository.getAllBikes()).thenReturn(expectedBikesEmpty);
        //WHEN
        List<Bike> actual = bikeService.getAllBikes();
        //THEN
        verify(bikeRepository).getAllBikes();
        Assertions.assertEquals(expectedBikesEmpty, actual);
    }
}