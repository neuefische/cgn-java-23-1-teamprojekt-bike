package com.bikes.backend.repository;

import com.bikes.backend.model.MongoUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class MongoUserRepositoryTest {

	MongoUser testUser;
	@Autowired
	MongoUserRepository repository;


	@BeforeEach
	public void setUp() {
		testUser = new MongoUser("Test ID", "Test username", "Test password", "BASIC");
	}


	@Nested
	@DisplayName("findByUsername()")
	class testFindById {

		@Test
		@DirtiesContext
		@DisplayName("...should return the user if the user exists")
		void findByUsername_returnsUserIfUserExists() {
			// GIVEN
			MongoUser expected = testUser;
			repository.save(testUser);
			// WHEN
			MongoUser actual = repository.findByUsername(testUser.username()).orElseThrow();
			// THEN
			assertEquals(expected, actual);
		}

		@Test
		@DisplayName("...should return an empty optional if the user does not exist")
		void findByUsername_returnsEmptyOptionalIfUserDoesNotExist() {
			// GIVEN
			Optional<MongoUser> expected = Optional.empty();
			// WHEN
			var actual = repository.findByUsername(testUser.username());
			// THEN
			assertEquals(expected, actual);
		}

	}

	@Nested
	@DisplayName("existsByUsername()")
	class testExistsByUsername {

		@Test
		@DirtiesContext
		@DisplayName("...should return true if the user exists")
		void existsByUsername_returnsTrueIfUserExists() {
			// GIVEN
			boolean expected = true;
			repository.save(testUser);
			// WHEN
			boolean actual = repository.existsByUsername(testUser.username());
			// THEN
			assertEquals(expected, actual);
		}

		@Test
		@DisplayName("...should return false if the user does not exist")
		void existsByUsername_returnsFalseIfUserDoesNotExist() {
			// GIVEN
			boolean expected = false;
			// WHEN
			boolean actual = repository.existsByUsername(testUser.username());
			// THEN
			assertEquals(expected, actual);
		}

	}
}