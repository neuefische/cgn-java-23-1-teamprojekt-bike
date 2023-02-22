package com.bikes.backend.controller;

import com.bikes.backend.model.Bike;
import com.bikes.backend.service.BikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BikeController {

    private final BikeService bikeService;


    @GetMapping("bikes")
    public Bike[] getAllBikes() {
        return bikeService.getAllBikes();
    }

}
