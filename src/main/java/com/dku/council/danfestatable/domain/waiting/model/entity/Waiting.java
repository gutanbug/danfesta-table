package com.dku.council.danfestatable.domain.waiting.model.entity;

import com.dku.council.danfestatable.domain.user.model.entity.User;
import com.dku.council.danfestatable.domain.waiting.model.WaitingStatus;
import com.dku.council.danfestatable.global.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "waiting")
public class Waiting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waiting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "danfesta_user_id")
    private User user;

    private int tableNumber;

    @Enumerated(EnumType.STRING)
    private WaitingStatus waitingStatus;

    @Builder
    private Waiting(User user, int tableNumber) {
        this.user = user;
        this.tableNumber = tableNumber;
        this.waitingStatus = WaitingStatus.WAITING;
    }

    public void changeToApproval() {
        this.waitingStatus = WaitingStatus.APPROVAL;
    }

    public void changeToReject() {
        this.waitingStatus = WaitingStatus.REJECT;
    }
}
