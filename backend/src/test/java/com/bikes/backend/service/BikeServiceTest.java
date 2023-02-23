package com.bikes.backend.service;

import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

class BikeServiceTest {

	BikeRepository bikeRepository = mock(BikeRepository.class);
	BikeService bikeService = new BikeService(bikeRepository);

	Bike testBike = new Bike("testId", "testBike");
	List<Bike> expectedBikes = List.of(testBike);
	List<Bike> expectedBikesEmpty = new ArrayList<>();


	@Nested
	@DisplayName("testing getAllBikes()")
	class getAllBikesTest {
		@Test
		@DisplayName("...returns all bikes if the repo is not empty")
		void getAllBikes_returnsAllBikesIfTheRepoIsNotEmpty() {
			//GIVEN
			when(bikeRepository.getAllBikes()).thenReturn(expectedBikes);
			//WHEN
			List<Bike> actual = bikeService.getAllBikes();
			//THEN
			verify(bikeRepository).getAllBikes();
			Assertions.assertEquals(expectedBikes, actual);
		}

		@Test
		@DisplayName("...returns an empty list if the repo is empty")
		void getAllBikes_returnsAnEmptyListIfTheRepoIsEmpty() {
			//GIVEN
			when(bikeRepository.getAllBikes()).thenReturn(expectedBikesEmpty);
			//WHEN
			List<Bike> actual = bikeService.getAllBikes();
			//THEN
			verify(bikeRepository).getAllBikes();
			Assertions.assertEquals(expectedBikesEmpty, actual);
		}
	}

	@Nested
	@DisplayName("testing getBikeById()")
	class getBikeByIdTest {

		@Test
		@DisplayName("...returns a bike if a bike with the given id exists")
		void getBikeById_returnsABikeIfABikeWithTheGivenIdExists() throws Exception {
			//GIVEN
			when(bikeRepository.getBikeById(testBike.id())).thenReturn(testBike);
			//WHEN
			Bike actual = bikeService.getBikeById(testBike.id());
			//THEN
			verify(bikeRepository).getBikeById(testBike.id());
			Assertions.assertEquals(testBike, actual);
		}

		@Test
		@DisplayName("...throws an exception if no bike with the given id exists")
		void getBikeById_throwExceptionIfNoBikeWithTheGivenIdExists() {
			//GIVEN
			String invalidId = "invalidId";
			//WHEN
			when(bikeRepository.getBikeById(invalidId)).thenThrow(new NoSuchElementException("No bike with id " + invalidId + " found"));
			//THEN
			Assertions.assertThrows(NoSuchElementException.class, () -> bikeService.getBikeById(invalidId));
		}
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


    @Test
    void addBike(){
        //GIVEN
        when(bikeRepository.addBike(testBike)).thenReturn(testBike);
        //WHEN
        Bike actual = bikeService.addBike(testBike);
        //THEN
        verify(bikeRepository).addBike(testBike);
        Assertions.assertEquals(testBike, actual);


    }
}
