package com.bikes.backend.model;


public record MongoUserResponseDTO(
        String id,
        String username,
        String role
) {
}
