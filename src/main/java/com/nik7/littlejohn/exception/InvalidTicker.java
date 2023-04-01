package com.nik7.littlejohn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Ticker do not exist")
public class InvalidTicker extends Exception {
    public InvalidTicker(Throwable cause) {
        super(cause);
    }
}
