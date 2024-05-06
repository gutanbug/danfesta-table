package com.dku.council.danfestatable.domain.order.repository;

import com.dku.council.danfestatable.domain.order.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.Order;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("select o from Orders o where o.id = :orderId")
    Optional<Orders> findByIdWithProgress(Long orderId);
}
