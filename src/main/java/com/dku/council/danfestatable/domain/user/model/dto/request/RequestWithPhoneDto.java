package com.dku.council.danfestatable.domain.user.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PROTECTED;

@Getter
@RequiredArgsConstructor(access = PROTECTED)
public class RequestWithPhoneDto {

    @NotBlank
    @Schema(description = "휴대폰 번호. 대시(-)는 있어도 되고 없어도 된다.", example = "010-1111-2222")
    private final String phone;
}
