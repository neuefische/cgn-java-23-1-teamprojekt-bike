package com.bikes.backend.repository;

import com.bikes.backend.model.Bike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class BikeRepositoryTest {
	Bike testBike;
	@Autowired
	BikeRepository repository;

	@BeforeEach
	public void setUp() {
		testBike = new Bike("testId", "testBike");
	}

	@DisplayName("getAllBikes()")
	@Nested
	class testGetAllBikes {

		@Test
		@DisplayName("...should return an empty array if there are no bikes in the database")
		@DirtiesContext
		void getAllBikes_returnsEmptyArrayIfRepoIsEmpty() {
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
		void getAllBikes_returnsAllBikesIfRepoIsNotEmpty() {
			//GIVEN
			List<Bike> expectedBikes = List.of(testBike);
			repository.save(testBike);
			//WHEN
			List<Bike> actualBikes = repository.findAll();
			//THEN
			assertEquals(expectedBikes, actualBikes);
		}

	}

}