package com.dku.council.danfestatable.domain.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestIncreaseHeartDto {
    @Schema(description = "증감할 하트 수", example = "1")
    private int requestHeart;

    public RequestIncreaseHeartDto(int requestHeart) {
        this.requestHeart = requestHeart;
    }
}
