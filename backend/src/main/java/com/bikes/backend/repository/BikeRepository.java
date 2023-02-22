package com.bikes.backend.repository;

import com.bikes.backend.model.Bike;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class BikeRepository {

    private final Map<String, Bike> bikeMap;

    public List<Bike> getAllBikes () {
        return bikeMap.values().stream().toList();
    }

    public Bike addBike(Bike bikeToAdd) {
        bikeMap.put(bikeToAdd.id(), bikeToAdd);
        return bikeToAdd;
    }
}
