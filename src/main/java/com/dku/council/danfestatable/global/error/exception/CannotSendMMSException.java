package com.dku.council.danfestatable.global.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class CannotSendMMSException extends LocalizedMessageException {
    public CannotSendMMSException() {
        super(HttpStatus.BAD_REQUEST, "failed.send-mms");
    }

    public CannotSendMMSException(Throwable t) {
        super(t, HttpStatus.BAD_REQUEST, "failed.send-mms");
    }
}
