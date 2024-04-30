package com.dku.council.danfestatable.domain.product.model.entity;

import com.dku.council.danfestatable.global.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private String description;

    private int quantity;

    private int requiredHeart;

    @Builder
    private Product(String name, String description, int quantity, int requiredHeart) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.requiredHeart = requiredHeart;
    }
}
