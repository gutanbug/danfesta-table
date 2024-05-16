package com.dku.council.danfestatable.infra.nhn.global.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class CannotSendSMSException extends LocalizedMessageException {
    public CannotSendSMSException(Throwable t) {
        super(t, HttpStatus.BAD_REQUEST, "failed.send-sms");
    }
}
