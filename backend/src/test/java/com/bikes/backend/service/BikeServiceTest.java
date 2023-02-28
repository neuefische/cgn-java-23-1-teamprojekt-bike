package com.bikes.backend.service;

import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class BikeServiceTest {

	@Autowired
	BikeRepository bikeRepository;
	IdService idService = mock(IdService.class);
	@Autowired
	BikeService bikeService;

	String testId = "testId";
	String invalidId = "invalidId";
	Bike testBike = new Bike(testId, "testBike");

	@Test
	@BeforeEach
	@DisplayName("set up test environment")
	void setUp() {
		bikeRepository.deleteAll();
		when(idService.generateId()).thenReturn(testBike.id());
	}

	@Nested
	@DisplayName("testing getAllBikes()")
	class getAllBikesTest {

		@Test
		@DisplayName("...returns an empty list if the repo is empty")
		void getAllBikes_returnsEmptyListIfTheRepoIsEmpty() {
			//GIVEN
			List<Bike> expected = new ArrayList<>();
			//WHEN
			List<Bike> actual = bikeService.getAllBikes();
			//THEN
			Assertions.assertEquals(expected, actual);
		}

		@Test
		@DisplayName("...returns all bikes if the repo is not empty")
		void getAllBikes_returnsAllBikesIfTheRepoIsNotEmpty() {
			//GIVEN
			bikeRepository.save(testBike);
			List<Bike> expected = List.of(testBike);
			//WHEN
			List<Bike> actual = bikeService.getAllBikes();
			//THEN
			Assertions.assertEquals(expected, actual);
		}

	}

	@Nested
	@DisplayName("testing getBikeById()")
	class getBikeByIdTest {

		@Test
		@DirtiesContext
		@DisplayName("...returns a bike if a bike with the given id exists")
		void getBikeById_returnsABikeIfABikeWithTheGivenIdExists() throws NoSuchBikeException {
			//GIVEN
			bikeRepository.save(testBike);
			//WHEN
			Bike actual = bikeService.getBikeById(testBike.id());
			//THEN
			Assertions.assertEquals(testBike, actual);
		}

		@Test
		@DisplayName("...throws an exception if no bike with the given id exists")
		void getBikeById_throwExceptionIfNoBikeWithTheGivenIdExists() throws NoSuchBikeException {
			//GIVEN
			Class<NoSuchBikeException> expected = NoSuchBikeException.class;
			//WHEN
			//THEN
			Assertions.assertThrows(expected, () -> bikeService.getBikeById(invalidId));
		}
	}

	@Nested
	@DisplayName("testing addBike()")
	class addBikeTest {

		@Test
		@DirtiesContext
		@DisplayName("...adds a bike to the database if the bike with the given id does not exist")
		void addBike_addsABikeToTheDatabaseIfTheBikeWithTheGivenIdDoesNotExist() {
			//GIVEN
			//WHEN
			Bike actual = bikeService.addBike(testBike);
			Bike expected = new Bike(actual.id(), testBike.title());
			//THEN
			Assertions.assertEquals(expected, actual);
		}

	}


}
