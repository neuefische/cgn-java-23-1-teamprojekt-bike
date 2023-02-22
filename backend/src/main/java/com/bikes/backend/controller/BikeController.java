package com.bikes.backend.controller;

import com.bikes.backend.model.Bike;
import com.bikes.backend.service.BikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BikeController {

	private final BikeService bikeService;

	@GetMapping("bikes")
	public List<Bike> getAllBikes() {
		return bikeService.getAllBikes();
	}

	@GetMapping("bikes/{id}")
	public Bike getBikeByID(@PathVariable String id) throws Exception {
		return bikeService.getBikeById(id);
	}

}


