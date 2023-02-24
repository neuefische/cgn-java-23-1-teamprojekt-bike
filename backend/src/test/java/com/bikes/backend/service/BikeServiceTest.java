package com.bikes.backend.service;

import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class BikeServiceTest {

	BikeRepository bikeRepository = mock(BikeRepository.class);
	IdService idService = mock(IdService.class);
    BikeService bikeService = new BikeService(bikeRepository, idService);

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

	@Nested
	@DisplayName("testing getBikeById()")
	class getBikeByIdTest {

		@Test
		@DisplayName("...returns a bike if a bike with the given id exists")
		void getBikeById_returnsABikeIfABikeWithTheGivenIdExists() {
			//GIVEN
			when(bikeRepository.getBikeById(testBike.id())).thenReturn(Optional.of(testBike));
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
			when(bikeRepository.getBikeById(invalidId)).thenThrow(new NoSuchBikeException());
			//THEN
			Assertions.assertThrows(NoSuchBikeException.class, () -> bikeService.getBikeById(invalidId));
		}
	}

	@DisplayName("testing addBike()")
    @Test
    void addBike(){
        //GIVEN
        when(bikeRepository.addBike(testBike)).thenReturn(testBike);
        when(idService.generateId()).thenReturn(testBike.id());
        //WHEN
        Bike actual = bikeService.addBike(testBike);
        //THEN
        verify(bikeRepository).addBike(testBike);
        Assertions.assertEquals(testBike, actual);


    }

	@Nested
	@DisplayName("testing deleteBike()")
	class deleteBikeTest {
		@Test
		@DisplayName("deleting existingBike")
		void deleteExistingBike() {
			//GIVEN
			when(bikeRepository.deleteBike(testBike.id())).thenReturn(Optional.ofNullable(testBike));
			//WHEN
			Bike actual = bikeService.deleteBike(testBike.id());
			//THEN
			verify(bikeRepository).deleteBike(testBike.id());
			Assertions.assertEquals(testBike, actual);
		}
		@Test
		@DisplayName("deleting invalid BikeId")
		void deleteBikeIdInvalid() {
			//GIVEN
			when(bikeRepository.deleteBike(testBike.id())).thenThrow(new NoSuchBikeException());
			//THEN
			Assertions.assertThrows(NoSuchBikeException.class, () -> bikeService.deleteBike("41"));
		}
	}
}
