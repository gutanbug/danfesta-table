package com.dku.council.danfestatable.domain.order.service;

import com.dku.council.danfestatable.domain.matchtable.exception.MatchingTableNotFoundException;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.matchtable.repository.MatchingTableRepository;
import com.dku.council.danfestatable.domain.order.exception.NotEnoughHeartException;
import com.dku.council.danfestatable.domain.order.exception.OrderNotFoundException;
import com.dku.council.danfestatable.domain.order.model.dto.list.SummarizedOrderDto;
import com.dku.council.danfestatable.domain.order.model.dto.request.RequestCreateOrderDto;
import com.dku.council.danfestatable.domain.order.model.entity.Orders;
import com.dku.council.danfestatable.domain.order.repository.OrderRepository;
import com.dku.council.danfestatable.domain.product.exception.ProductNotFoundException;
import com.dku.council.danfestatable.domain.product.exception.ProductQuantityNotEnoughException;
import com.dku.council.danfestatable.domain.product.model.entity.Product;
import com.dku.council.danfestatable.domain.product.repository.ProductRepository;
import com.dku.council.danfestatable.domain.product_order.model.entity.ProductOrders;
import com.dku.council.danfestatable.domain.product_order.repository.ProductOrdersRepository;
import com.dku.council.danfestatable.global.error.exception.NotGrantedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final MatchingTableRepository tableRepository;
    private final ProductOrdersRepository productOrdersRepository;

    public List<SummarizedOrderDto> list(Long userId) {
        MatchingTable table = tableRepository.findByUserId(userId)
                .orElseThrow(MatchingTableNotFoundException::new);
        return productOrdersRepository.findAllByTableId(table.getId()).stream()
                .map(po -> new SummarizedOrderDto(po.getOrders(), po.getProduct()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Long createOrder(Long userId, Long productId, RequestCreateOrderDto dto) {
        MatchingTable table = tableRepository.findByUserId(userId)
                .orElseThrow(MatchingTableNotFoundException::new);
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        int totalRequiredHeart = product.getRequiredHeart() * dto.getAmount();

        if(product.getQuantity() < dto.getAmount()) {
            throw new ProductQuantityNotEnoughException();
        } else if(totalRequiredHeart > table.getTotalHeart()) {
            throw new NotEnoughHeartException();
        }

        Orders orders = Orders.builder()
                .matchingTable(table)
                .orderCount(dto.getAmount())
                .build();
        orders = orderRepository.save(orders);
        ProductOrders po = ProductOrders.builder()
                .orders(orders)
                .product(product)
                .orderSumHeart(totalRequiredHeart)
                .build();
        productOrdersRepository.save(po);
        product.decreaseQuantity(dto.getAmount());

        return orders.getId();
    }

    @Transactional
    public void cancelOrder(Long userId, Long orderId) {
        MatchingTable table = tableRepository.findByUserId(userId)
                .orElseThrow(MatchingTableNotFoundException::new);
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
        Product product = productRepository.findByOrderId(orderId)
                .orElseThrow(ProductNotFoundException::new);
        if(order.getMatchingTable().getId() != table.getId()) {
            throw new NotGrantedException();
        } else {
            order.markedAsCanceled();
            product.increaseQuantity(order.getOrderCount());
        }
    }
}
