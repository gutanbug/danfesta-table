package com.dku.council.danfestatable.domain.matchtable.model.entity;

import com.dku.council.danfestatable.domain.matchtable.model.TableStatus;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import com.dku.council.danfestatable.global.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "matching_table")
public class MatchingTable extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matching_table_id")
    private Long id;

    @OneToMany(mappedBy = "matchingTable", orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @NotNull
    private int tableNumber;

    private int totalHeart;

    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    private MatchingTable(int tableNumber) {
        this.tableNumber = tableNumber;
        this.totalHeart = 2;
        this.tableStatus = TableStatus.ACTIVE;
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusHours(2);
    }

    public void addUser(User user) {
        if(!users.contains(user)){
            users.add(user);
            user.setMatchingTable(this);
        }
    }

    public void addTime() {
        this.endTime = this.endTime.plusHours(1);
    }

    public void markedAsInactive() {
        this.tableStatus = TableStatus.INACTIVE;
    }

    public void useHeart(int orderSumHeart) {
        this.totalHeart -= orderSumHeart;
    }

    public void increaseHeart(int requestHeart, boolean isPlus) {
        if(isPlus)
            this.totalHeart += requestHeart;
        else
            this.totalHeart -= requestHeart;
    }

    public void sendHeart(int heartCount) {
        this.totalHeart -= heartCount;
    }

    public void receiveHeart(int heartCount) {
        this.totalHeart += heartCount;
    }
}
