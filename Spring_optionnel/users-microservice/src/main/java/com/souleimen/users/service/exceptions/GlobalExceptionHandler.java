package com.souleimen.users.service.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice 
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	 public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception,
	 WebRequest webRequest){
		
	 ErrorDetails errorDetails = new ErrorDetails(
			 LocalDateTime.now(),
			 exception.getMessage(),
			 webRequest.getDescription(false),
			 "USER_EMAIL_ALREADY_EXISTS"
	 );
	 return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	 }
	 @ExceptionHandler(Exception.class) 
	    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, 
	                                                                          
	WebRequest webRequest){ 
	 
	        ErrorDetails errorDetails = new ErrorDetails( 
	                LocalDateTime.now(), 
	                exception.getMessage(), 
	                webRequest.getDescription(false), 
	                "INTERNAL SERVER ERROR" 
	        		); 
	        
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); 
	      }
	 
	 
	 @ExceptionHandler(ExpiredTokenException.class) 
	 public ResponseEntity<ErrorDetails> 
	 handleExpiredTokenException(ExpiredTokenException exception, 
			 WebRequest webRequest){ 

		 ErrorDetails errorDetails = new ErrorDetails( 
				 LocalDateTime.now(), 
				 exception.getMessage(), 
				 webRequest.getDescription(false), 
				 "EXPIRED_TOKEN" 
				 ); 
		 return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); 
	 } 
	 
	 
	 @ExceptionHandler(InvalidTokenException.class) 
	 public ResponseEntity<ErrorDetails> 
	 handleInvalidTokenException(InvalidTokenException exception,                                                                        
			 WebRequest webRequest){ 

		 ErrorDetails errorDetails = new ErrorDetails( 
				 LocalDateTime.now(), 
				 exception.getMessage(), 
				 webRequest.getDescription(false), 
				 "INVALID_TOKEN" 
				 ); 

		 return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); 
	 }

  } 
        