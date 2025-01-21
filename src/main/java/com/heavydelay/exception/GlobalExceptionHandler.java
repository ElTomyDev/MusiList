package com.heavydelay.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.heavydelay.model.payload.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        return new ResponseEntity<>(
            ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Conflict in route definition. Please check the routes in the controller.")
            .message("routes cannot be identified: " + ex.getMessage())
            .path(request.getDescription(false))
            .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MissingServletRequestParameterException ex, WebRequest request){
        return new ResponseEntity<>(
            ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Missing Parameter")
            .message("Required parameter '" + ex.getMessage() + "' is missing")
            .path(request.getDescription(false))
            .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request){
        return new ResponseEntity<>(
            ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Type Mismatch Error")
            .message("Invalid type providad for parameter: " + ex.getMessage())
            .path(request.getDescription(false))
            .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex , WebRequest request){
        return new ResponseEntity<>(
            ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.METHOD_NOT_ALLOWED.value())
            .error("Method Not Allowed")
            .message("The HTTP method" + ex.getMessage() + "is not supported for this endpoint.")
            .path(request.getDescription(false))
            .build(), HttpStatus.METHOD_NOT_ALLOWED
        );
    }

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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(
            ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Illegal Argument")
            .message("A constraint violation occurred: " + ex.getMessage() )
            .path(request.getDescription(false))
            .build(), HttpStatus.BAD_REQUEST
        );
    }
}
