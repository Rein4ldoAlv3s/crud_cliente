package com.reinaldo.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.reinaldo.services.exceptions.DataIntegrityException;
import com.reinaldo.services.exceptions.NullPointerException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<StandardError> nullPointerException(NullPointerException n) {
		StandardError error = new StandardError(System.currentTimeMillis(), 
				HttpStatus.NOT_FOUND.value(), n.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrityException(DataIntegrityException n) {
		StandardError error = new StandardError(System.currentTimeMillis(), 
				HttpStatus.NOT_FOUND.value(), n.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
