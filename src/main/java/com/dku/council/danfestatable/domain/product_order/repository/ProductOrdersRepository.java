package com.dku.council.danfestatable.domain.product_order.repository;

import com.dku.council.danfestatable.domain.product_order.model.entity.ProductOrders;
import io.lettuce.core.ScanIterator;
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

    @Query("select po from ProductOrders po join fetch po.orders o join fetch po.product p where o.orderStatus = 'PROGRESS'")
    List<ProductOrders> findAllWithProgress();

    @Query("select po from ProductOrders po join fetch po.orders o join fetch po.product p where o.orderStatus = 'APPROVAL' or o.orderStatus = 'REJECT'")
    List<ProductOrders> findAllWithApprovalOrReject();
}
