package com.dku.council.danfestatable.domain.product_order.repository;

import com.dku.council.danfestatable.domain.product_order.model.entity.ProductOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductOrdersRepository extends JpaRepository<ProductOrders, Long> {
    @Query("select po from ProductOrders po join po.orders o join o.matchingTable t where t.id = :id")
    List<ProductOrders> findAllByTableId(@Param("id") Long id);
}
