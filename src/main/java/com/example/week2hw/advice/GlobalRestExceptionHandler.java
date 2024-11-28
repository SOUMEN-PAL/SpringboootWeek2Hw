package com.example.week2hw.advice;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.annotations.processing.HQL;
import org.modelmapper.MappingException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    ResponseEntity<ApiResponse<?>> getErrorResponse(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse<>(apiError) , apiError.getStatus());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponse<?>> handleDataAccessException(DataAccessException e){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .subErrors(List.of(e.getMessage()))
                .build();

        return  getErrorResponse(apiError);
    }

    @ExceptionHandler(MappingException.class)
    public ResponseEntity<ApiResponse<?>> handleMappingExceptions(MappingException e){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .subErrors(List.of("Unable to Map Data to Database with wrong parameters"))
                .build();

        return  getErrorResponse(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEntityNotFoundException(EntityNotFoundException e){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();

        return  getErrorResponse(apiError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return getErrorResponse(apiError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handelHttpMessageNotReadable(HttpMessageNotReadableException e){
        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return getErrorResponse(apiError);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ApiResponse<?>> handelDateTimeParsingException(DateTimeParseException e){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Enter the date in YYYY-MM-dd format!!")
                .subErrors(List.of(e.getMessage()))
                .build();
        return getErrorResponse(apiError);
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<ApiResponse<?>> handelDateTimeParsingException(DateTimeException e){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Enter the date in YYYY-MM-dd format!!")
                .subErrors(List.of(e.getMessage()))
                .build();
        return getErrorResponse(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class )
    public ResponseEntity<ApiResponse<?>> handelMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<String> subErrors =  e
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Invalid Data parameters")
                .subErrors(subErrors)
                .build();
        return getErrorResponse(apiError);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<?>> hendelIllegalStateException(IllegalArgumentException e){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
        return getErrorResponse(apiError);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handelException(Exception e){
        e.printStackTrace();
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
        return getErrorResponse(apiError);
    }



}
