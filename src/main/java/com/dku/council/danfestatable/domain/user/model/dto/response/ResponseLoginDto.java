package com.dku.council.danfestatable.domain.user.model.dto.response;

import com.dku.council.danfestatable.global.auth.jwt.AuthenticationToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ResponseLoginDto {
    private final String accessToken;
    private final String refreshToken;

    public ResponseLoginDto(AuthenticationToken token) {
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
    }
}
