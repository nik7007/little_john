package com.nik7.littlejohn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidPageException extends Exception {
    public InvalidPageException(String message) {
        super(message);
    }
}
