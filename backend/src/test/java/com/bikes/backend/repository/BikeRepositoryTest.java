package com.bikes.backend.repository;

import com.bikes.backend.model.Bike;

class BikeRepositoryTest {
	Bike testBike;
	BikeRepository nonEmptyRepo;
	BikeRepository testRepoEmpty;

//    @BeforeEach
//    public void setUp() {
//        testBike = new Bike("testId", "testBike");
//        nonEmptyRepo = new BikeRepository(Map.of(testBike.id(), testBike));
//        testRepoEmpty = new BikeRepository(new HashMap<>());
//    }
//
//    @Test
//    void getAllBikesEmptyRepo() {
//        //GIVEN
//        List<Bike> expectedBikes = new ArrayList<>();
//        //WHEN
//        List<Bike> actualBikes = testRepoEmpty.getAllBikes();
//        //THEN
//        assertEquals(expectedBikes, actualBikes);
//    }
//
//    @Test
//    void getAllBikesNonEmptyRepo() {
//        //GIVEN
//        List<Bike> expectedBikes = List.of(testBike);
//        //WHEN
//        List<Bike> actualBikes = nonEmptyRepo.getAllBikes();
//        //THEN
//        assertEquals(expectedBikes, actualBikes);
//    }
//

}