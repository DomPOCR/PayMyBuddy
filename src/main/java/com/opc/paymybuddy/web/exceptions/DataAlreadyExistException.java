package com.opc.paymybuddy.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.CONFLICT)
public class DataAlreadyExistException extends ResponseStatusException {

    public DataAlreadyExistException(HttpStatus status, String message) {
        super(status, message);
    }
    public DataAlreadyExistException(HttpStatus status) {
        super(status);
    }
}
