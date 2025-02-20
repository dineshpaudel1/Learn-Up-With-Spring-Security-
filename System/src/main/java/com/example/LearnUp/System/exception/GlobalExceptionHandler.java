package com.example.LearnUp.System.exception;

import com.example.LearnUp.System.model.ValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler({org.springframework.web.bind.MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationResponse> handleValidationExceptions(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ValidationResponse validationResponse = ValidationResponse.builder()
                .errors(errors)
                .build();

        return ResponseEntity.badRequest().body(validationResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateUsername(RuntimeException ex){
        return new ResponseEntity<>( new ExceptionResponse().builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                        .build(),HttpStatus.CONFLICT);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> invalidCredential(BadCredentialsException ex){
        return new ResponseEntity<>(new ExceptionResponse().builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(ex.getMessage())
                .build(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponse> usernameNotFound(UsernameNotFoundException ex){
        return  new ResponseEntity<>(new ExceptionResponse().builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }

}
