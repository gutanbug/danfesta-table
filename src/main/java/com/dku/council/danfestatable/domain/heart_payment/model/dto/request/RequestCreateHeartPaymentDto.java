package com.dku.council.danfestatable.domain.heart_payment.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCreateHeartPaymentDto {

    private int requiredHeartCount;

    public RequestCreateHeartPaymentDto(int requiredHeartCount) {
        this.requiredHeartCount = requiredHeartCount;
    }
}
