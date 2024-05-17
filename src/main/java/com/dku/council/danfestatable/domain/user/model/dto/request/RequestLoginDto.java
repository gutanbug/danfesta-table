package com.dku.council.danfestatable.domain.user.model.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestLoginDto {
    private String loginId;
    private String password;

    public RequestLoginDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
