package com.dku.council.danfestatable.domain.heart_payment.model;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    WAITING("대기중"),
    APPROVAL("승인됨"),
    REJECT("거절됨");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }
}
