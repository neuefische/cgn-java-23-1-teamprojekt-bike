package com.bikes.backend.service;

import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class BikeServiceTest {

	BikeRepository bikeRepository = mock(BikeRepository.class);
	IdService idService = mock(IdService.class);
	BikeService bikeService = new BikeService(bikeRepository, idService);

	Bike testBike = new Bike("testId", "testBike");
	List<Bike> expectedBikes = List.of(testBike);
	List<Bike> expectedBikesEmpty = new ArrayList<>();

	@Test
	void addBike() {
		//GIVEN
		when(bikeRepository.save(testBike)).thenReturn(testBike);
		when(idService.generateId()).thenReturn(testBike.id());
		//WHEN
		Bike actual = bikeService.addBike(testBike);
		//THEN
		verify(bikeRepository).save(testBike);
		Assertions.assertEquals(testBike, actual);


	}

//	@Nested
//	@DisplayName("testing getAllBikes()")
//	class getAllBikesTest {
//		@Test
//		@DisplayName("...returns all bikes if the repo is not empty")
//		void getAllBikes_returnsAllBikesIfTheRepoIsNotEmpty() {
//			//GIVEN
//
//			//WHEN
//			List<Bike> actual = bikeService.getAllBikes();
//			//THEN
//			verify(bikeRepository).findAll();
//			Assertions.assertEquals(expectedBikes, actual);
//		}
//
//		@Test
//		@DisplayName("...returns an empty list if the repo is empty")
//		void getAllBikesEmptyRepo() {
//			//GIVEN
//			when(bikeRepository.getAllBikes()).thenReturn(expectedBikesEmpty);
//			//WHEN
//			List<Bike> actual = bikeService.getAllBikes();
//			//THEN
//			verify(bikeRepository).getAllBikes();
//			Assertions.assertEquals(expectedBikesEmpty, actual);
//		}

//	}
//
//	@Nested
//	@DisplayName("testing getBikeById()")
//	class getBikeByIdTest {
//
//		@Test
//		@DisplayName("...returns a bike if a bike with the given id exists")
//		void getBikeById_returnsABikeIfABikeWithTheGivenIdExists() throws Exception {
//			//GIVEN
//			when(bikeRepository.findById(testBike.id())).thenReturn(Optional.of(testBike));
//			//WHEN
//			Bike actual = bikeService.getBikeById(testBike.id());
//			//THEN
//			verify(bikeRepository).findById(testBike.id());
//			Assertions.assertEquals(testBike, actual);
//		}
//
//		@Test
//		@DisplayName("...throws an exception if no bike with the given id exists")
//		void getBikeById_throwExceptionIfNoBikeWithTheGivenIdExists() throws Exception {
//			//GIVEN
//			String invalidId = "invalidId";
//			//WHEN
//			when(bikeRepository.findById(invalidId)).thenThrow(new NoSuchBikeException());
//			//THEN
//			Assertions.assertThrows(NoSuchBikeException.class, () -> bikeService.getBikeById(invalidId));
//		}
//	}
}
