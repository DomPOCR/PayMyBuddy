package com.opc.paymybuddy.web.controller;


import com.opc.paymybuddy.web.exceptions.DataAlreadyExistException;
import com.opc.paymybuddy.web.exceptions.DataMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataMissingException.class)
    public final ResponseEntity<String> handleDataMissingException(DataMissingException ex, WebRequest request) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public final ResponseEntity<String> handleDataMissingException(DataAlreadyExistException ex, WebRequest request) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
    }

}
