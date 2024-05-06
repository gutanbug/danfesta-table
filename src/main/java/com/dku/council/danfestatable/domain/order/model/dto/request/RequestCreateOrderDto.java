package com.dku.council.danfestatable.domain.order.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCreateOrderDto {
    private  int amount;

    public RequestCreateOrderDto(int amount) {
        this.amount = amount;
    }
}
