package com.bikes.backend.controller;

import com.bikes.backend.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/bikes/images")
@RequiredArgsConstructor
public class UploadController {

	private final CloudinaryService cloudinaryService;

	@PostMapping
	public String uploadPhoto(@RequestParam("file") MultipartFile photo) throws IOException {
		return cloudinaryService.uploadPhoto(photo);
	}

}
