package com.dku.council.danfestatable.domain.message.model.entity;

import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.global.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "message")
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_table_id")
    private MatchingTable senderTable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_table_id")
    private MatchingTable receiverTable;

    @NotNull
    @Column(name = "send_heart_count")
    private int sendHeartCount;

    @NotNull
    private String message;

    @Builder
    public Message(MatchingTable senderTable, MatchingTable receiverTable, int sendHeartCount, String message) {
        this.senderTable = senderTable;
        this.receiverTable = receiverTable;
        this.sendHeartCount = sendHeartCount;
        this.message = message;
    }
}
