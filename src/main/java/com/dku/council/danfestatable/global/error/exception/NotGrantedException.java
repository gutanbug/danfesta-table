package com.dku.council.danfestatable.global.error.exception;

import org.springframework.http.HttpStatus;

public class NotGrantedException extends LocalizedMessageException {

    public NotGrantedException() {
        super(HttpStatus.UNAUTHORIZED, "required.granted");
    }

    public NotGrantedException(Throwable t) {
        super(t, HttpStatus.UNAUTHORIZED, "required.granted");
    }
}
