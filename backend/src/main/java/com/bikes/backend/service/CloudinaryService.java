package com.bikes.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

	private final Cloudinary cloudinary;

	public CloudinaryService() {
		Map config = new HashMap<>();
		config.put("cloud_name", System.getenv("CLOUDINARY_CLOUD_NAME")); // CLOUDINARY_CLOUD_NAME
		config.put("api_key", System.getenv("CLOUDINARY_API_KEY"));  // CLOUDINARY_API_KEY
		config.put("api_secret", System.getenv("CLOUDINARY_API_SECRET")); // CLOUDINARY_API_SECRET
		cloudinary = new Cloudinary(config);
	}

	public String uploadPhoto(MultipartFile photo) throws IOException {
		Map result = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
		return result.get("url").toString();
	}
}