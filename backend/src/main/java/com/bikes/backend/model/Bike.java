package com.bikes.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("bikes")
public record Bike(String id, String title, String imageUrl) {
}
