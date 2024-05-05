package com.dku.council.danfestatable.domain.user.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends LocalizedMessageException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "notfound.user");
    }
}
