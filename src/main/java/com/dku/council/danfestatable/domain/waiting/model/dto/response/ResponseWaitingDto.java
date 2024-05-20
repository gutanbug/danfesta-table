package com.dku.council.danfestatable.domain.waiting.model.dto.response;

import com.dku.council.danfestatable.domain.waiting.model.entity.Waiting;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ResponseWaitingDto {

    private final Long id;
    private final String name;
    private final String phone;
    private final int tableNumber;
    private final String status;
    private final String createdAt;

    public ResponseWaitingDto(Waiting waiting) {
        this.id = waiting.getId();
        this.name = waiting.getUser().getName();
        this.phone = waiting.getUser().getPhone();
        this.tableNumber = waiting.getTableNumber();
        this.status = waiting.getWaitingStatus().name();
        this.createdAt = cleanTime(waiting.getCreatedAt());
    }

    private String cleanTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
        return time.format(formatter);
    }
}
