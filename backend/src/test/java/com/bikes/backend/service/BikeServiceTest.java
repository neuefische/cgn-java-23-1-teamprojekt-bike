package com.bikes.backend.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class BikeServiceTest {

	@Autowired
	BikeRepository bikeRepository;
	IdService mockedIdService = mock(IdService.class);
	@Autowired
	BikeService bikeService;

	String testId = "Some test ID";
	String invalidId = "Some invalid ID";
	Bike testBike = new Bike(testId, "Mega bike 9000");
	BikeDTO testBikeDTO = new BikeDTO("Mega bike 9000");
	BikeWithIdDTO testBikeWithIdDTO = new BikeWithIdDTO(testId, "Mega bike 9000");

	@BeforeEach
	@DisplayName("set up test environment")
	void setUp() {
		bikeService = new BikeService(bikeRepository, mockedIdService);
		when(mockedIdService.generateId()).thenReturn(testBike.id());
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
		@DirtiesContext
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
			Bike expected = testBike;
			//WHEN
			Bike actual = bikeService.addBike(testBikeDTO);
			//THEN
			verify(mockedIdService).generateId();
			Assertions.assertEquals(expected, actual);
		}

	}

	@Nested
	@DisplayName("testing updateBike()")
	class updateBikeTest {

		@Test
		@DirtiesContext
		@DisplayName("...throws an exception if the bike with the given id does not exist yet")
		void updateBike_throwsExceptionIfTheBikeWithTheGivenIdDoesNotExist() {
			//GIVEN
			Class<NoSuchBikeException> expected = NoSuchBikeException.class;
			//WHEN + THEN
			Assertions.assertThrows(expected, () -> bikeService.updateBike(testBikeWithIdDTO));
		}

		@Test
		@DirtiesContext
		@DisplayName("...updates an existing bike in the database if the bike with the given id does already exist")
		void updateBike_addsABikeToTheDatabaseIfTheBikeWithTheGivenIdDoesExist() {
			//GIVEN
			bikeRepository.save(testBike);
			BikeWithIdDTO updatedBike = new BikeWithIdDTO(testBike.id(), "Updated bike title");
			Bike expected = new Bike(testBike.id(), "Updated bike title");
			//WHEN
			Bike actual = bikeService.updateBike(updatedBike);
			//THEN
			Assertions.assertEquals(expected, actual);
		}

	}

	@Nested
	@DisplayName("testing deleteBike()")
	class deleteBikeTest {
		@Test
		@DisplayName("...deletes an existing bike and returns it back")
		void deleteExistingBike() {
			//GIVEN
			bikeRepository.save(testBike);
			//WHEN
			Bike actual = bikeService.deleteBike(testBike.id());
			//THEN
			Assertions.assertEquals(testBike, actual);
		}

		@Test
		@DisplayName("...throws an exception if the bike with the given id does not exist")
		void deleteBikeIdInvalid() {
			//GIVEN
			//THEN
			Assertions.assertThrows(NoSuchBikeException.class, () -> bikeService.deleteBike("41"));
		}

	}

}
