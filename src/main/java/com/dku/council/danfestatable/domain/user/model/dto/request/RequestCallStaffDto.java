package com.dku.council.danfestatable.domain.user.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCallStaffDto {

    private String message;

    public RequestCallStaffDto(String message) {
        this.message = message;
    }
}
