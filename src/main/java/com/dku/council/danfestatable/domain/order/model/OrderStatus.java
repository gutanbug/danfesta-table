package com.dku.council.danfestatable.domain.order.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PROGRESS("진행중"),
    APPROVAL("승인"),
    REJECT("거절"),
    CANCELED("취소");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
