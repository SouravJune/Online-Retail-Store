package com.msd.product.exceptionhandler;

import com.msd.product.exceptions.ProductAlreadyExistException;
import com.msd.product.exceptions.RecordNotFoundException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> validationErrors = new HashMap<>();
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

		validationErrorList.forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String validationMsg = error.getDefaultMessage();
			validationErrors.put(fieldName, validationMsg);
		});

		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleGlobalException(Exception exception, WebRequest webRequest) {
		ProblemDetail problemDetails = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		problemDetails.setTitle("InternalServerError");
		problemDetails.setDetail(exception.getMessage());
		problemDetails.setType(URI.create("http://localhost:8184/api/v1/errors/internal-error"));
		problemDetails.setInstance(URI.create(webRequest.getDescription(false)));
		problemDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		problemDetails.setProperty("timestamp", LocalDateTime.now());
		problemDetails.setProperty("port", 8184);
		problemDetails.setProperty("host", "localhost");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(problemDetails);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ProblemDetail> handlerNotRecordFoundException(RecordNotFoundException exception, WebRequest webRequest){

		ProblemDetail problemDetails = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		problemDetails.setTitle("NotRecordFoundException");
		problemDetails.setDetail(exception.getMessage());
		problemDetails.setType(URI.create("http://localhost:8184/api/errors/resource-not-found"));
		problemDetails.setInstance(URI.create(webRequest.getDescription(false)));
		problemDetails.setStatus(HttpStatus.NOT_FOUND);
		problemDetails.setProperty("timestamp", LocalDateTime.now());
		problemDetails.setProperty("port", 8184);
		problemDetails.setProperty("host", "localhost");

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problemDetails);
	}

	@ExceptionHandler(ProductAlreadyExistException.class)
	public ResponseEntity<ProblemDetail> handlerCartDoesNotExistException(ProductAlreadyExistException exception, WebRequest webRequest){

		ProblemDetail problemDetails = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		problemDetails.setTitle("CartDoesNotExistException");
		problemDetails.setDetail(exception.getMessage());
		problemDetails.setType(URI.create("http://localhost:8184/api/errors/cart-not-found"));
		problemDetails.setInstance(URI.create(webRequest.getDescription(false)));
		problemDetails.setStatus(HttpStatus.NOT_FOUND);
		problemDetails.setProperty("timestamp", LocalDateTime.now());
		problemDetails.setProperty("port", 8184);
		problemDetails.setProperty("host", "localhost");

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problemDetails);
	}
}
