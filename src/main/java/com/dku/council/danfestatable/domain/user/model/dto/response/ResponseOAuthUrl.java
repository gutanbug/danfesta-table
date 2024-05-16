package com.dku.council.danfestatable.domain.user.model.dto.response;

import lombok.Getter;

@Getter
public class ResponseOAuthUrl {

    private final String url;

    public ResponseOAuthUrl(String url) {
        this.url = url;
    }
}
