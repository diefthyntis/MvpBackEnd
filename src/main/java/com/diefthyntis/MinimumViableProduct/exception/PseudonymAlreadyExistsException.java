package com.diefthyntis.MinimumViableProduct.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Pseudonym already exists.")
public class PseudonymAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public PseudonymAlreadyExistsException(String sentence) {
        super(sentence);
    }
}



