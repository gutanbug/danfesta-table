package com.dku.council.danfestatable.domain.admin.dto.list;

import com.dku.council.danfestatable.domain.product_order.model.entity.ProductOrders;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class SummarizedOrderForAdminDto {

    private final Long orderId;
    private final int tableNumber;
    private final String productName;
    private final int orderCount;
    private final int orderSumHeart;
    private final String orderStatus;
    private final String orderDate;

    public SummarizedOrderForAdminDto(ProductOrders po) {
        this.orderId = po.getOrders().getId();
        this.tableNumber = po.getOrders().getMatchingTable().getTableNumber();
        this.productName = po.getProduct().getName();
        this.orderCount = po.getOrders().getOrderCount();
        this.orderSumHeart = po.getOrderSumHeart();
        this.orderStatus = po.getOrders().getOrderStatus().getStatus();
        this.orderDate = po.getOrders().getCreatedAt().format(DateTimeFormatter.ofPattern("MM/dd HH:mm:ss"));
    }
}
