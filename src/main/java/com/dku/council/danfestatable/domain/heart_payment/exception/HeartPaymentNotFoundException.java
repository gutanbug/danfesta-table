package com.dku.council.danfestatable.domain.heart_payment.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class HeartPaymentNotFoundException extends LocalizedMessageException {

    public HeartPaymentNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "notfound.heart-payment");
    }
}
