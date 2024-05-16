package com.dku.council.danfestatable.domain.order.model.dto.list;

import com.dku.council.danfestatable.domain.order.model.entity.Orders;
import com.dku.council.danfestatable.domain.product.model.entity.Product;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class SummarizedOrderDto {
    private String productName;
    private int orderCount;
    private int orderTotalHeart;
    private String orderStatus;
    private String orderDate;

    public SummarizedOrderDto(Orders orders, Product product) {
        this.productName = product.getName();
        this.orderCount = orders.getOrderCount();
        this.orderTotalHeart = orders.getOrderCount() * product.getRequiredHeart();
        this.orderStatus = orders.getOrderStatus().getStatus();
        this.orderDate = orders.getCreatedAt().format(DateTimeFormatter.ofPattern("MM/dd HH:mm:ss"));
    }
}
