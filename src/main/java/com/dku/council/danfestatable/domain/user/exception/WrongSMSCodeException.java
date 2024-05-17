package com.dku.council.danfestatable.domain.user.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class WrongSMSCodeException extends LocalizedMessageException {

    public WrongSMSCodeException() {
        super(HttpStatus.BAD_REQUEST, "invalid.sms-code");
    }
}
