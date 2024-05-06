package com.dku.council.danfestatable.domain.order.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class NotEnoughHeartException extends LocalizedMessageException {
    public NotEnoughHeartException() {
        super(HttpStatus.BAD_REQUEST, "invalid.not-enough.heart");
    }
}
