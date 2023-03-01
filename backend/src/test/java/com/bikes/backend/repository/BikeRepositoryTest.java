package com.bikes.backend.repository;

import com.bikes.backend.model.Bike;
import com.bikes.backend.service.NoSuchBikeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class BikeRepositoryTest {
	Bike testBike;
	@Autowired
	BikeRepository repository;

	String invalidId = "Some invalid ID";

	@BeforeEach
	public void setUp() {
		testBike = new Bike("Some test ID", "Mega bike 9000");
	}

	@DisplayName("findAll()")
	@Nested
	class testFindAll {

		@Test
		@DisplayName("...should return an empty array if there are no bikes in the database")
		@DirtiesContext
		void findAll_returnsEmptyArrayIfRepoIsEmpty() {
			//GIVEN
			List<Bike> expectedBikes = new ArrayList<>();
			//WHEN
			List<Bike> actualBikes = repository.findAll();
			//THEN
			assertEquals(expectedBikes, actualBikes);
		}

		@Test
		@DisplayName("...should return all bikes if there are bikes in the database")
		@DirtiesContext
		void findAll_returnsAllBikesIfRepoIsNotEmpty() {
			//GIVEN
			List<Bike> expectedBikes = List.of(testBike);
			repository.save(testBike);
			//WHEN
			List<Bike> actualBikes = repository.findAll();
			//THEN
			assertEquals(expectedBikes, actualBikes);
		}

	}

	@DisplayName("findById()")
	@Nested
	class testFindById {

		@Test
		@DisplayName("...should return a bike if the bike with the given id does exist")
		@DirtiesContext
		void findById_returnsABikeIfThereIsABikeWithTheGivenId() {
			//GIVEN
			repository.save(testBike);
			//WHEN
			Bike actualBike = repository.findById(testBike.id()).orElseThrow(NoSuchBikeException::new);
			//THEN
			assertEquals(testBike, actualBike);
		}

		@Test
		@DisplayName("...should return an empty optional if the bike with the given id does not exist")
		@DirtiesContext
		void findById_returnsEmptyOptionalIfThereIsNoBikeWithTheGivenId() {
			//GIVEN
			Optional<Bike> expectedBike = Optional.empty();
			//WHEN
			Optional<Bike> actualBike = repository.findById(invalidId);
			//THEN
			assertEquals(expectedBike, actualBike);
		}

	}

	@DisplayName("save()")
	@Nested
	class testAddBike {

		@Test
		@DisplayName("...should add bike to the database if the bike does not exist yet")
		@DirtiesContext
		void save_addsBikeToDatabase() {
			//GIVEN
			Bike expectedBike = testBike;
			repository.findById(testBike.id()).ifPresent(repository::delete);
			//WHEN
			repository.save(testBike);
			Bike actualBike = repository.findById(testBike.id()).get();
			//THEN
			assertEquals(expectedBike, actualBike);
		}

		@Test
		@DisplayName("...should update the bike in the database if the bike already exists")
		@DirtiesContext
		void save_updatesBikeInDatabase() {
			//GIVEN
			Bike expectedBike = new Bike(testBike.id(), "Mega bike 9000 ver.2");
			repository.save(testBike);
			//WHEN
			repository.save(expectedBike);
			Bike actualBike = repository.findById(testBike.id()).get();
			//THEN
			assertEquals(expectedBike, actualBike);
		}

	}


}