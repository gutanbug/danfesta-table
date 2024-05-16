package com.dku.council.danfestatable.domain.admin.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestCreateProductDto {
    private final String name;
    private final String description;
    private final String quantity;
    private final String requiredHeart;
}
