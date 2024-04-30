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
    private MatchingTable(int tableNumber, LocalDateTime startTime, LocalDateTime endTime) {
        this.tableNumber = tableNumber;
        this.totalHeart = 2;
        this.tableStatus = TableStatus.ACTIVE;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addUser(User user) {
        users.add(user);
        user.setMatchingTable(this);
    }
}