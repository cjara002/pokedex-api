package com.pokedex.pokedex_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

// @ControllerAdvice tells Spring "this class handles exceptions
// thrown by ANY controller in the entire application"
@ControllerAdvice
public class GlobalExceptionHandler {
    // @ExceptionHandler tells Spring which specific exception
    // this method should intercept
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleNotFound(HttpClientErrorException ex) {
        // ResponseEntity is Spring's way of giving you full control
        // over the HTTP response — status code, headers, and body
        // Think of it like returning IActionResult in .NET
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
