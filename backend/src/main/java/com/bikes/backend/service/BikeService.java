package com.bikes.backend.service;


import com.bikes.backend.model.Bike;
import com.bikes.backend.repository.BikeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    public Bike[] getAllBikes(){
        return bikeRepository.getAllBikes();
    }


}
