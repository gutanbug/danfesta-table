package com.dku.council.danfestatable.domain.user.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class NotSMSSentException extends LocalizedMessageException {

    public NotSMSSentException() {
        super(HttpStatus.BAD_REQUEST, "required.sms-sending");
    }
}
