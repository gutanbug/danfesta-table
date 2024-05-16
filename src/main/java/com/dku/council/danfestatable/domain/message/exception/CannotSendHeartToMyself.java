package com.dku.council.danfestatable.domain.message.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class CannotSendHeartToMyself extends LocalizedMessageException {
    
    public CannotSendHeartToMyself() {
        super(HttpStatus.BAD_REQUEST, "failed.send-heart-myself");
    }
}
