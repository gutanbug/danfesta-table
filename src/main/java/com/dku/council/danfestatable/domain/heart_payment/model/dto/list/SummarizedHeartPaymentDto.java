package com.dku.council.danfestatable.domain.heart_payment.model.dto.list;

import com.dku.council.danfestatable.domain.heart_payment.model.entity.HeartPayment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class SummarizedHeartPaymentDto {

    private final Long id;

    private final int tableNumber;

    private final int requiredHeartCount;

    private final int totalPrice;

    private final String status;

    private final String createdAt;

    public SummarizedHeartPaymentDto(HeartPayment payment) {
        this.id = payment.getId();
        this.tableNumber = payment.getMatchingTable().getTableNumber();
        this.requiredHeartCount = payment.getRequiredHeartCount();
        this.totalPrice = payment.getTotalPrice();
        this.status = payment.getPaymentStatus().getStatus();
        this.createdAt = changeDate(payment.getCreatedAt());
    }

    private String changeDate(LocalDateTime createdAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
        return createdAt.format(formatter);
    }
}
