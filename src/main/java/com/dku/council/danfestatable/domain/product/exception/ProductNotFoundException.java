package com.dku.council.danfestatable.domain.product.exception;

import com.dku.council.danfestatable.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends LocalizedMessageException {

    public ProductNotFoundException() {
        super(HttpStatus.NOT_FOUND, "notfound.product");
    }
}
