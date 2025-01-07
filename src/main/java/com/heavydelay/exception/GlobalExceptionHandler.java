package com.heavydelay.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.heavydelay.model.payload.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(
            ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error("Not Found")
            .message(ex.getMessage())
            .path(request.getDescription(false))
            .build(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(BindException ex, WebRequest request){
        List<String> errorList = ex.getBindingResult()
                                   .getAllErrors()
                                   .stream()
                                   .map(error -> error.getDefaultMessage())
                                   .collect(Collectors.toList());
        return new ResponseEntity<>(
            ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Validation Error")
            .message("Problems with field requirements")
            .path(request.getDescription(false))
            .details(errorList)
            .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request){
        return new ResponseEntity<>(
            ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.CONFLICT.value())
            .error("Database Error")
            .message("A constraint violation occurred: " + ex.getMostSpecificCause().getMessage() )
            .path(request.getDescription(false))
            .build(), HttpStatus.CONFLICT
        );
    }
}
