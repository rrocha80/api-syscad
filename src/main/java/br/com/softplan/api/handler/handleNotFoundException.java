package br.com.softplan.api.handler;

import java.util.Date;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.softplan.api.error.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class handleNotFoundException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	  public final ResponseEntity<ExceptionResponse> handleNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		String msg = null; 
		ExceptionResponse exceptionResponse;
		
		if (ex.getMessage().indexOf(" messageTemplate='") > 0) {
			msg = ex.getMessage().substring(ex.getMessage().indexOf(" messageTemplate='") + 18, ex.getMessage().length() - 4);
			
			 exceptionResponse = new ExceptionResponse(new Date(), msg,
				        request.getDescription(false),HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
		} else {
			exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
			        request.getDescription(false),HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
			
		}
		
	    

	    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
	  }

}
