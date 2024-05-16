package com.dku.council.danfestatable.domain.product.repository;

import com.dku.council.danfestatable.domain.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.name = :name")
    Optional<Product> findByName(@Param("name") String name);

    @Query("select p from Product p join ProductOrders po on p.id = po.product.id " +
            "join Orders o on po.orders.id = o.id where o.id = :orderId and o.orderStatus = 'PROGRESS'")
    Optional<Product> findByOrderId(@Param("orderId") Long orderId);
}
