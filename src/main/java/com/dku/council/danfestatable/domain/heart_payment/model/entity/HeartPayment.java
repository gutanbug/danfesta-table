package com.dku.council.danfestatable.domain.heart_payment.model.entity;

import com.dku.council.danfestatable.domain.heart_payment.model.PaymentStatus;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.global.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "heart_payment")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartPayment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_payment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "matching_table_id")
    private MatchingTable matchingTable;

    private int requiredHeartCount;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Builder
    public HeartPayment(MatchingTable table, int requiredHeartCount, int totalPrice) {
        this.matchingTable = table;
        this.requiredHeartCount = requiredHeartCount;
        this.totalPrice = totalPrice;
        this.paymentStatus = PaymentStatus.WAITING;
    }

    public void approve() {
        this.paymentStatus = PaymentStatus.APPROVAL;
        this.matchingTable.increaseHeart(this.requiredHeartCount, true);
    }

    public void reject() {
        this.paymentStatus = PaymentStatus.REJECT;
    }
}
