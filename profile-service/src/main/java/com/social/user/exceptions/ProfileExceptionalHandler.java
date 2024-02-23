package com.social.user.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProfileExceptionalHandler extends ResponseEntityExceptionHandler
{

	// global exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest)
	{
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) 
	{
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors())
		{
			details.add(error.getDefaultMessage());
		}
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date()).message(request.getDescription(true)).details(ex.getLocalizedMessage()).build();
		return new ResponseEntity<>(error, status);
	}

	@ExceptionHandler(
	{AccessDeniedException.class, BadCredentialsException.class})
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request)
	{
		return new ResponseEntity<Object>("Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status,
		WebRequest request)
	{
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date()).message(request.getDescription(false)).details(ex.getMostSpecificCause().getLocalizedMessage()).build();
		return new ResponseEntity<>(error, status);
	}
}
