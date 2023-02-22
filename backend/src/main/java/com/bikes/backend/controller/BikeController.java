package com.bikes.backend.controller;

import com.bikes.backend.model.Bike;
import com.bikes.backend.service.BikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bikes")
public class BikeController {

    private final BikeService bikeService;


    @GetMapping("/")
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }
    @PostMapping("/")
    public Bike addBike(@RequestBody Bike bikeToAdd) {
        return bikeService.addBike(bikeToAdd);
    }
}
