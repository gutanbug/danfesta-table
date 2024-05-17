package com.dku.council.danfestatable.domain.user.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestVerifySMSCodeDto {

    @NotBlank
    @Schema(description = "핸드폰 번호", example = "01012345678")
    private final String phoneNumber;

    @NotBlank
    @Schema(description = "인증 코드", example = "123456")
    private final String code;
}
