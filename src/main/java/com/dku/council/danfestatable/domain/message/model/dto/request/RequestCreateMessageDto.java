package com.dku.council.danfestatable.domain.message.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCreateMessageDto {

    private int sendHeartCount;

    private String message;
}
