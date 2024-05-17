package com.dku.council.danfestatable.domain.user.model.dto.response;

import com.dku.council.danfestatable.domain.user.model.entity.User;
import com.dku.council.danfestatable.global.auth.jwt.AuthenticationToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ResponseLoginDto {
    private final String accessToken;
    private final String refreshToken;
    private final boolean isAdmin;

    public ResponseLoginDto(AuthenticationToken token, User user) {
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
        this.isAdmin = user.getUserRole().isAdmin();
    }
}
