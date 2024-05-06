package com.dku.council.danfestatable.domain.order.repository;

import com.dku.council.danfestatable.domain.order.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.Order;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
