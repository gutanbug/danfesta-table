package com.dku.council.danfestatable.infra.nhn.global.model.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseToken {
    private final Access access;

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Access {
        private final Token token;
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Token {
        private final String id;
    }
}
