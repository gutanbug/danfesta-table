package com.dku.council.danfestatable.domain.message.model.dto.response;

import com.dku.council.danfestatable.domain.message.model.entity.Message;
import lombok.Getter;

@Getter
public class ResponseMessageDto {

    private final int senderTableNumber;

    private final int heartCount;

    private final String message;

    public ResponseMessageDto(Message message) {
        this.senderTableNumber = message.getSenderTable().getTableNumber();
        this.heartCount = message.getSendHeartCount();
        this.message = message.getMessage();
    }
}
