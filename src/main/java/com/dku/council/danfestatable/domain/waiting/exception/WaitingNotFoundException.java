package com.dku.council.danfestatable.domain.waiting.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class WaitingNotFoundException extends LocalizedMessageException {

    public WaitingNotFoundException() {
        super(HttpStatus.NOT_FOUND, "notfound.waiting");
    }
}
