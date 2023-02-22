package com.bikes.backend.repository;

import com.bikes.backend.model.Bike;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@AllArgsConstructor
@Getter
@ToString
public class BikeRepository {

    private final Map<String, Bike> bikeMap;

    public Bike[] getAllBikes () {
        return bikeMap.values().toArray(new Bike[0]);
    }

}
