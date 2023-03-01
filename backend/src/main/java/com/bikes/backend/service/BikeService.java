package com.bikes.backend.service;

import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BikeService {

	private final BikeRepository bikeRepository;

	private final IdService idService;

	public List<Bike> getAllBikes() {
		return bikeRepository.findAll();
	}

	public Bike getBikeById(String id) {
		return bikeRepository.findById(id).orElseThrow(NoSuchBikeException::new);
	}

	public Bike addBike(Bike incomingBike) {
		Bike bikeToAdd = new Bike(idService.generateId(), incomingBike.title());
		return bikeRepository.save(bikeToAdd);
	}

	public Bike updateBike(Bike incomingBike) throws NoSuchBikeException {
		if (!bikeRepository.existsById(incomingBike.id())) {
			throw new NoSuchBikeException();
		}
		return bikeRepository.save(incomingBike);
	}


	public Bike deleteBike(String id) {
		if (!bikeRepository.existsById(id)) {
			throw new NoSuchBikeException();
		}
		Bike bikeToDelete = bikeRepository.findById(id).get();
		bikeRepository.deleteById(id);
		return bikeToDelete;
	}

}
