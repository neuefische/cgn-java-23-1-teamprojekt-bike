package com.bikes.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Bike not found.")
public class NoSuchBikeException extends NoSuchElementException {
}
