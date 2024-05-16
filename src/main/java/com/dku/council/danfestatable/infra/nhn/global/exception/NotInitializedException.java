package com.dku.council.danfestatable.infra.nhn.global.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class NotInitializedException extends LocalizedMessageException {

    public NotInitializedException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "unexpected");
    }
}
