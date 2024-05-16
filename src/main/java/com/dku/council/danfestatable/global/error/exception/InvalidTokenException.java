package com.dku.council.danfestatable.global.error.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends LocalizedMessageException {

    public InvalidTokenException() {
        super(HttpStatus.UNAUTHORIZED, "invalid.token");
    }
}
