package com.dku.council.danfestatable.domain.product.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class ProductQuantityNotEnoughException extends LocalizedMessageException {

    public ProductQuantityNotEnoughException() {
        super(HttpStatus.BAD_REQUEST, "invalid.product-quantity");
    }
}
