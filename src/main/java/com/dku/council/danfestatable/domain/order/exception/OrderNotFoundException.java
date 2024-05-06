package com.dku.council.danfestatable.domain.order.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends LocalizedMessageException {

    public OrderNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "notfound.order");
    }
}
