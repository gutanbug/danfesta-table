package com.dku.council.danfestatable.global.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;

public class NotSupportedMethodException extends LocalizedMessageException {
    public NotSupportedMethodException(Throwable t) {
        super(t, HttpStatus.METHOD_NOT_ALLOWED, "notsupport.http-method");
    }
}
