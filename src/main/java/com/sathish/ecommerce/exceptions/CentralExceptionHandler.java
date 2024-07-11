package com.sathish.ecommerce.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CentralExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> CustomMethodArgumentNotValidExceptionhandler(MethodArgumentNotValidException e){
        Map<String,String> res = new HashMap<>();
        e.getAllErrors().forEach(err->{
            String fieldName = ((FieldError)err).getField();
            String fieldErr = err.getDefaultMessage();
            res.put(fieldName,fieldErr);
        });
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> CustomResourceNotFoundExceptionHandler(ResourceNotFoundException e){
        Map<String,String> res = new HashMap<>();
        res.put("message",e.getLocalizedMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

}
