package com.bikes.backend.service;

import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Service
public class BikeService {

	private final BikeRepository bikeRepository;

	private final IdService idService;

	public List<Bike> getAllBikes() {
		return bikeRepository.getAllBikes();
	}

	public Bike getBikeById(String id) {
		return bikeRepository.getBikeById(id).orElseThrow(NoSuchBikeException::new);
	}

	public Bike addBike(Bike incomingBike) {
		Bike bikeToAdd = new Bike(idService.generateId(), incomingBike.title());
		return bikeRepository.addBike(bikeToAdd);
	}
}
