package com.lrtech.ecommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lrtech.ecommerce.dto.CustomError;
import com.lrtech.ecommerce.dto.ValidationError;
import com.lrtech.ecommerce.services.exceptions.DataBaseException;
import com.lrtech.ecommerce.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ControllerExceptionHandler {
  

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException message, HttpServletRequest request){
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomError error = new CustomError(Instant.now(),status.value(), message.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }
  @ExceptionHandler(DataBaseException.class)
  public ResponseEntity<CustomError> dataBase(DataBaseException message, HttpServletRequest request){
    HttpStatus status = HttpStatus.BAD_REQUEST;
    CustomError error = new CustomError(Instant.now(),status.value(), message.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException message, HttpServletRequest request){
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    ValidationError error = new ValidationError(Instant.now(),status.value(), "Dados Inválidos ", request.getRequestURI());

    for (FieldError fieldError : message.getBindingResult().getFieldErrors()) {
      error.addError(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return ResponseEntity.status(status).body(error);
  }
}
