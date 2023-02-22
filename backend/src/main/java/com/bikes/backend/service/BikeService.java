package com.bikes.backend.service;


import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    public List<Bike> getAllBikes(){
        return bikeRepository.getAllBikes();
    }


}
