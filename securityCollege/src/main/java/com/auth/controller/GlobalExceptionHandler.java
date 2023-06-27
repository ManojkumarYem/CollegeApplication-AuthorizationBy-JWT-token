package com.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth.dao.ApiResponse;
import com.auth.exception.ExpiredException;

@RestControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(value = MethodArgumentNotValidException.class)
//	public ApiResponse methodInvalidException(MethodArgumentNotValidException ex) {
//
//		Map<String, String> errorMap = new HashMap<>();
//		ApiResponse response = new ApiResponse();
//	ex.getBindingResult().getFieldErrors().forEach(error->{
//		errorMap.put(error.getField(),error.getDefaultMessage());
//	});
//		
//		response.setError(errorMap);
//		response.setStatus(HttpStatus.BAD_REQUEST.value());
//		return response;
//
//	}
//	
	@ExceptionHandler(value = ExpiredException.class)
	public ApiResponse APIException(ExpiredException ex) {
		System.out.println("Exception Accured");
		ApiResponse response = new ApiResponse();
		response.setError(ex.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return response;

	}

	@ExceptionHandler(value = { ServletException.class })
	public ApiResponse servletException(ServletException e) {
		System.out.println("TokenEcpired Exception Accured");
		String message = e.getMessage();
		ApiResponse response = new ApiResponse();
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		if (message.equals("token_expired")) {
			response.setError(e.getMessage());
			response.setStatus(HttpStatus.BAD_REQUEST.value());

		}
		response.setError(e.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return response;

	}
//	@ExceptionHandler(value = Exception.class)
//	public Object APIException(Exception ex) {
//		
//		response.setError(ex.getMessage());
//		response.setStatus(HttpStatus.BAD_REQUEST.value());
//		return response;
//
//	}

}
