package com.dku.council.danfestatable.domain.product.model.dto.list;

import com.dku.council.danfestatable.domain.product.model.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SummarizedProductDto {
    private final Long id;
    private final String name;
    private final String description;
    private final int quantity;
    private final int requiredHeart;

    public SummarizedProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.quantity = product.getQuantity();
        this.requiredHeart = product.getRequiredHeart();
    }
}
