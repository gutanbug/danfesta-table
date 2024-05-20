package com.dku.council.danfestatable.domain.message.model.dto.response;

import com.dku.council.danfestatable.domain.message.model.entity.Message;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ResponseMessageDto {

    private final int senderTableNumber;

    private final int heartCount;

    private final String message;

    private final String createdAt;

    public ResponseMessageDto(Message message) {
        this.senderTableNumber = message.getSenderTable().getTableNumber();
        this.heartCount = message.getSendHeartCount();
        this.message = message.getMessage();
        this.createdAt = cleanTime(message.getCreatedAt());
    }

    private String cleanTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
        return time.format(formatter);
    }
}
