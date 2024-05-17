package com.dku.council.danfestatable.domain.user.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class AlreadyUserExistException extends LocalizedMessageException {

    public AlreadyUserExistException() {
        super(HttpStatus.BAD_REQUEST, "already.user");
    }
}
