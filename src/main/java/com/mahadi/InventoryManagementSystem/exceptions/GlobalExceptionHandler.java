package com.mahadi.InventoryManagementSystem.exceptions;

import com.mahadi.InventoryManagementSystem.dto.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mahadi.InventoryManagementSystem.dto.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleAllExceptions(Exception exception) {
		Response response = Response.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(exception.getMessage()).build();
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Response> handleNotFoundException(Exception exception) {
		Response response = Response.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.message(exception.getMessage()) // exception message
				.build();

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NameValueRequiredException.class)
	public ResponseEntity<Response> handleNameValueRequiredException(Exception exception) {
		Response response = Response.builder()
				.status(HttpStatus.BAD_REQUEST.value()) // or other status code
				.message(exception.getMessage()) // exception message
				.build();

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<Response> handleInvalidCredentialException(Exception exception) {
		Response response = Response.builder()
				.status(HttpStatus.BAD_REQUEST.value()) // or other status code
				.message(exception.getMessage()) // exception message
				.build();

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
