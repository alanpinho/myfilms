package com.personalprojects.myfilms.myfilms.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.personalprojects.myfilms.myfilms.exception.BadRequestException;
import com.personalprojects.myfilms.myfilms.exception.BadRequestExceptionDetails;
import com.personalprojects.myfilms.myfilms.exception.ValidationExceptionDetails;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre){
		return new ResponseEntity<>(
				BadRequestExceptionDetails.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Bad Request Exception, Check the documentation")
				.details(bre.getMessage())
				.developerMessage(bre.getClass().getName())
				.build(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
		
		// Get all fields error
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		// Get field errors
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		
		// Get default message of field errors
		String fieldsDefaultMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
		
		return new ResponseEntity<ValidationExceptionDetails>(
				ValidationExceptionDetails.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Bad Request Exception. Invalid fields")
				.details("Check the fields message")
				.developerMessage(exception.getClass().getName())
				.fields(fields)
				.fieldsMessage(fieldsDefaultMessage)
				.build(), HttpStatus.BAD_REQUEST);
	}
	
}
