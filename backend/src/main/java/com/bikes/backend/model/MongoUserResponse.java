package com.bikes.backend.model;


public record MongoUserResponse(
		String id,
		String username,
		String role
) {
}
