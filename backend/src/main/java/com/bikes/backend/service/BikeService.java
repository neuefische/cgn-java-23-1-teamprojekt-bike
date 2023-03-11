package com.bikes.backend.service;

import com.bikes.backend.model.Bike;
import com.bikes.backend.model.BikeDTO;
import com.bikes.backend.model.BikeWithIdDTO;
import com.bikes.backend.repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

	public Bike addBike(BikeDTO incomingBike) {
		final String DEFAULT_BIKE_IMAGE = "https://res.cloudinary.com/diikwvy8u/image/upload/v1678453345/8-WnQm41fywTkFZPX_nywzmq.png";
		Bike bikeToAdd = new Bike(idService.generateId(), incomingBike.title(), DEFAULT_BIKE_IMAGE);
		return bikeRepository.save(bikeToAdd);
	}

	public Bike updateBike(BikeWithIdDTO incomingBike) throws NoSuchBikeException {
		if (!bikeRepository.existsById(incomingBike.id())) {
			throw new NoSuchBikeException();
		}
		Bike result = new Bike(incomingBike.id(), incomingBike.title(), incomingBike.imageUrl());
		return bikeRepository.save(result);
	}

	public Bike deleteBike(String id) {
		Optional<Bike> bikeToDelete = bikeRepository.findById(id);
		if (bikeToDelete.isEmpty()) {
			throw new NoSuchBikeException();
		} else {
			bikeRepository.deleteById(id);
			return bikeToDelete.get();
		}
	}


}
