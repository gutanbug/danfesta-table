package com.dku.council.danfestatable.global.auth.jwt;

public interface AuthenticationToken {

    String getAccessToken();

    String getRefreshToken();
}
