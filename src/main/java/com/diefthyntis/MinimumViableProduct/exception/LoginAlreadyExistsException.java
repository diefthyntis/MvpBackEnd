package com.diefthyntis.MinimumViableProduct.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Login already exists.")
public class LoginAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public LoginAlreadyExistsException(String sentence) {
        super(sentence);
    }
}