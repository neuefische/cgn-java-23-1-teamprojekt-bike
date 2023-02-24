package com.bikes.backend.repository;

import com.bikes.backend.model.Bike;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BikeRepository {

	private final Map<String, Bike> bikeMap;

	public List<Bike> getAllBikes() {
		return bikeMap.values().stream().toList();
	}

	public Optional<Bike> getBikeById(String id) {
		return Optional.ofNullable(bikeMap.get(id));
	}


    public Bike addBike(Bike bikeToAdd) {
        bikeMap.put(bikeToAdd.id(), bikeToAdd);
        return bikeToAdd;
    }
	public Optional<Bike> deleteBike(String id) {
		return Optional.ofNullable(bikeMap.remove(id));
	}
}
