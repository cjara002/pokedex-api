package com.pokedex.pokedex_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleNotFound(HttpClientErrorException ex) {
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Pokemon not found. Please check the Pokemon name and try again.");
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleServiceUnavailable(ResourceAccessException ex) {
        return ResponseEntity
        .status(HttpStatus.SERVICE_UNAVAILABLE)
        .body("Unable to reach PokeApi. Please try again later.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex){
        return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Something went wrong. Please try again.");
    }
}
