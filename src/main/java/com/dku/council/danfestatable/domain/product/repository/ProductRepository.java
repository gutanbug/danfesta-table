package com.dku.council.danfestatable.domain.product.repository;

import com.dku.council.danfestatable.domain.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
