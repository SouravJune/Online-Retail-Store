package com.msd.customer.exceptionhandler;

import com.msd.customer.exceptions.NoDeleteException;
import com.msd.customer.exceptions.NoUpdateException;
import com.msd.customer.exceptions.NotRecordFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleGlobalException(Exception exception, WebRequest webRequest) {
		ProblemDetail problemDetails = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		problemDetails.setTitle("InternalServerError");
		problemDetails.setDetail(exception.getMessage());
		problemDetails.setType(URI.create("http://localhost:8181/api/v1/errors/interl-error"));
		problemDetails.setInstance(URI.create(webRequest.getDescription(false)));
		problemDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		problemDetails.setProperty("timestamp", LocalDateTime.now());
		problemDetails.setProperty("port", 8181);
		problemDetails.setProperty("host", "localhost");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(problemDetails);
	}

	@ExceptionHandler(NoDeleteException.class)
	public ResponseEntity<ProblemDetail> handlerNoDeleteException(NoDeleteException exception, WebRequest webRequest){

		ProblemDetail problemDetails = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		problemDetails.setTitle("NoDeleteException");
		problemDetails.setDetail(exception.getMessage());
		problemDetails.setType(URI.create("http://localhost:8181/api/errors/resource-not-found"));
		problemDetails.setInstance(URI.create(webRequest.getDescription(false)));
		problemDetails.setStatus(HttpStatus.NOT_FOUND);
		problemDetails.setProperty("timestamp", LocalDateTime.now());
		problemDetails.setProperty("port", 8181);
		problemDetails.setProperty("host", "localhost");

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problemDetails);
	}

	@ExceptionHandler(NotRecordFoundException.class)
	public ResponseEntity<ProblemDetail> handlerNotRecordFoundException(NotRecordFoundException exception, WebRequest webRequest){

		ProblemDetail problemDetails = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		problemDetails.setTitle("NotRecordFoundException");
		problemDetails.setDetail(exception.getMessage());
		problemDetails.setType(URI.create("http://localhost:8181/api/errors/resource-not-found"));
		problemDetails.setInstance(URI.create(webRequest.getDescription(false)));
		problemDetails.setStatus(HttpStatus.NOT_FOUND);
		problemDetails.setProperty("timestamp", LocalDateTime.now());
		problemDetails.setProperty("port", 8181);
		problemDetails.setProperty("host", "localhost");

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problemDetails);
	}

	@ExceptionHandler(NoUpdateException.class)
	public ResponseEntity<ProblemDetail> handlerNoUpdateException(NoUpdateException exception, WebRequest webRequest){

		ProblemDetail problemDetails = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		problemDetails.setTitle("NoUpdateException");
		problemDetails.setDetail(exception.getMessage());
		problemDetails.setType(URI.create("http://localhost:8181/api/errors/resource-not-found"));
		problemDetails.setInstance(URI.create(webRequest.getDescription(false)));
		problemDetails.setStatus(HttpStatus.NOT_FOUND);
		problemDetails.setProperty("timestamp", LocalDateTime.now());
		problemDetails.setProperty("port", 8181);
		problemDetails.setProperty("host", "localhost");

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problemDetails);
	}
}
