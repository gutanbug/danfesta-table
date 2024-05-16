package com.dku.council.danfestatable.domain.order.model.entity;

import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.order.model.OrderStatus;
import com.dku.council.danfestatable.global.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_table_id")
    private MatchingTable matchingTable;

    private int orderCount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    private Orders(MatchingTable matchingTable, int orderCount) {
        this.matchingTable = matchingTable;
        this.orderCount = orderCount;
        this.orderStatus = OrderStatus.PROGRESS;
    }

    public void markedAsCanceled() {
        this.orderStatus = OrderStatus.CANCELED;
    }

    public void changeToApproval() {
        this.orderStatus = OrderStatus.APPROVAL;
    }

    public void changeToReject() {
        this.orderStatus = OrderStatus.REJECT;
    }
}
