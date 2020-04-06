package com.mylabs.myfuel.api.excepition;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandleValidationExceptions {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErros> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        ApiErros err = new ApiErros();
        err.addErros(erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}