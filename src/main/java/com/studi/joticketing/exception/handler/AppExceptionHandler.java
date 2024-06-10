package com.studi.joticketing.exception.handler;

import com.studi.joticketing.DTO.ErrorMessage;
import com.studi.joticketing.exception.EntityNotFoundException;
import com.studi.joticketing.exception.WrongCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {WrongCredentialsException.class})
    public ResponseEntity<Object> WrongCredentialsException(WrongCredentialsException ex) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }
}
