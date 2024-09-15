package com.tamil.bookmyshow.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import com.tamil.bookmyshow.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler{
	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> movieIsNull(MovieNotFoundException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> adminIsNull(AdminNotFoundException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ArtistNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> artistIsNull(ArtistNotFoundException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidDetailException.class)
	public ResponseEntity<ResponseStructure<String>> invalidDetail(InvalidDetailException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> customerNotFound(CustomerNotFoundException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(PasswordException.class)
	public ResponseEntity<ResponseStructure<String>> password(PasswordException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<ResponseStructure<String>> email(EmailException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> ticket(TicketNotFoundException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(TimeException.class)
	public ResponseEntity<ResponseStructure<String>> time(TimeException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(ShowNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> show(ShowNotFoundException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(TheatreNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> theatre(TheatreNotFoundException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> city(CityNotFoundException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler(MultipleShowException.class)
	public ResponseEntity<ResponseStructure<String>> multipleShows(MultipleShowException exception){
		ResponseStructure<String>str=new ResponseStructure<>();
		str.setMessage(exception.getMessage());
		str.setObject(exception.getClass().getSimpleName());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
}
	@ExceptionHandler
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		ResponseStructure<Object> structure = new ResponseStructure<>();
		Map<String, String> hashmap = new HashMap<>();
				
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			hashmap.put(field, message);
		}
		structure.setMessage("add proper details");
		structure.setStatus(HttpStatus.FORBIDDEN.value());
		structure.setObject(hashmap);
		return new ResponseEntity<Object>(structure, HttpStatus.BAD_REQUEST);
	}
}
