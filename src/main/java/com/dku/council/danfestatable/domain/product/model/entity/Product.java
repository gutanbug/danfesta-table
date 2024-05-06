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

    public void update(String name, String description, String quantity, String requiredHeart) {
        this.name = name;
        this.description = description;
        this.quantity = Integer.parseInt(quantity);
        this.requiredHeart = Integer.parseInt(requiredHeart);
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }

    public void increaseQuantity(int orderCount) {
        this.quantity += orderCount;
    }
}
