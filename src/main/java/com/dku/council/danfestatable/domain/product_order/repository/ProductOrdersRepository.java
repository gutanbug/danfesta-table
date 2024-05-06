package com.dku.council.danfestatable.domain.product_order.repository;

import com.dku.council.danfestatable.domain.product_order.model.entity.ProductOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductOrdersRepository extends JpaRepository<ProductOrders, Long> {
    @Query("select po from ProductOrders po join po.orders o join o.matchingTable t where t.id = :id " +
            "and o.orderStatus != 'CANCELED'")
    List<ProductOrders> findAllByTableId(@Param("id") Long id);

    @Query("select po from ProductOrders po join fetch po.orders o where o.id = :orderId")
    Optional<ProductOrders> findByOrderId(@Param("orderId") Long orderId);
}
