package com.bikes.backend.controller;

import com.bikes.backend.model.Bike;
import com.bikes.backend.service.BikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bikes")
public class BikeController {

	private final BikeService bikeService;


    @GetMapping("/")
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }
    @GetMapping("/{id}")
    public Bike getBikeByID(@PathVariable String id) throws NoSuchElementException {
        return bikeService.getBikeById(id);
    }
    @PostMapping("/")
    public Bike addBike(@RequestBody Bike bikeToAdd) {
        return bikeService.addBike(bikeToAdd);
    }
}
