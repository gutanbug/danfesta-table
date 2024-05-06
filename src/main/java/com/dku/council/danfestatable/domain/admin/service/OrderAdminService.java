package com.dku.council.danfestatable.domain.admin.service;

import com.dku.council.danfestatable.domain.admin.dto.list.SummarizedOrderForAdminDto;
import com.dku.council.danfestatable.domain.matchtable.exception.MatchingTableNotFoundException;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.matchtable.repository.MatchingTableRepository;
import com.dku.council.danfestatable.domain.order.exception.NotEnoughHeartException;
import com.dku.council.danfestatable.domain.order.exception.OrderNotFoundException;
import com.dku.council.danfestatable.domain.order.model.dto.list.SummarizedOrderDto;
import com.dku.council.danfestatable.domain.order.model.entity.Orders;
import com.dku.council.danfestatable.domain.order.repository.OrderRepository;
import com.dku.council.danfestatable.domain.product.model.entity.Product;
import com.dku.council.danfestatable.domain.product_order.model.entity.ProductOrders;
import com.dku.council.danfestatable.domain.product_order.repository.ProductOrdersRepository;
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
public class OrderAdminService {

    private final OrderRepository orderRepository;
    private final ProductOrdersRepository productOrdersRepository;
    private final MatchingTableRepository tableRepository;

    public List<SummarizedOrderForAdminDto> list() {
        return productOrdersRepository.findAll().stream()
                .map(SummarizedOrderForAdminDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void approvalOrder(Long orderId) {
        ProductOrders po = productOrdersRepository.findByOrderId(orderId)
                .orElseThrow(OrderNotFoundException::new);
        Orders orders = po.getOrders();
        MatchingTable table = tableRepository.findByIdWithActive(orders.getMatchingTable().getId())
                .orElseThrow(MatchingTableNotFoundException::new);

        if(table.getTotalHeart() < po.getOrderSumHeart()){
            throw new NotEnoughHeartException();
        }
        table.useHeart(po.getOrderSumHeart());
        orders.changeToApproval();
    }

    @Transactional
    public void rejectOrder(Long orderId) {
        ProductOrders po = productOrdersRepository.findByOrderId(orderId)
                .orElseThrow(OrderNotFoundException::new);
        Product product = po.getProduct();
        Orders orders = po.getOrders();
        product.increaseQuantity(orders.getOrderCount());
        orders.changeToReject();
    }
}
