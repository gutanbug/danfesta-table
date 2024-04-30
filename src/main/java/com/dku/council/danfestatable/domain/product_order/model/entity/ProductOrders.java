package com.dku.council.danfestatable.domain.product_order.model.entity;

import com.dku.council.danfestatable.domain.order.model.entity.Orders;
import com.dku.council.danfestatable.domain.product.model.entity.Product;
import com.dku.council.danfestatable.global.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "product_orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOrders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int orderSumHeart;

    @Builder
    private ProductOrders(Orders orders, Product product, int orderSumHeart) {
        this.orders = orders;
        this.product = product;
        this.orderSumHeart = orderSumHeart;
    }
}
