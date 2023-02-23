package com.bikes.backend.repository;

import com.bikes.backend.model.Bike;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
@AllArgsConstructor
public class BikeRepository {

	private final Map<String, Bike> bikeMap;

	public List<Bike> getAllBikes() {
		return bikeMap.values().stream().toList();
	}

	public Bike getBikeById(String id) throws NoSuchElementException {
		if (bikeMap.get(id) != null) {
			return bikeMap.get(id);
		} else {
			throw new NoSuchElementException("Bike with id " + id + " does not exist.");
		}
	}


}
