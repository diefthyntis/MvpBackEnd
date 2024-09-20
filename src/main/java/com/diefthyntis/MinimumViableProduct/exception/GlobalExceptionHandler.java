package com.diefthyntis.MinimumViableProduct.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*
 * GlobalExceptionHandler est en veille des exceptions qu'il faut gérer de façon particulière
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(LoginAlreadyExistsException.class)
    public ResponseEntity<String> handleloginAlreadyExistsException(LoginAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
	
	@ExceptionHandler({SpeakerNotFoundException.class})
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
	
}
