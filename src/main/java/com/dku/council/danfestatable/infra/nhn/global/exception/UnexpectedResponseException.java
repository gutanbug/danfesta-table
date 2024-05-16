package com.dku.council.danfestatable.infra.nhn.global.exception;

public class UnexpectedResponseException extends RuntimeException {
    public UnexpectedResponseException(String message) {
        super(message);
    }
}
