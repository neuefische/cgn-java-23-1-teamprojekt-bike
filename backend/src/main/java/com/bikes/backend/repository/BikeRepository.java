package com.bikes.backend.repository;

import com.bikes.backend.model.Bike;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends MongoRepository<Bike, String> {
}
