package com.dku.council.danfestatable.domain.admin.service;

import com.dku.council.danfestatable.domain.admin.dto.request.RequestCreateProductDto;
import com.dku.council.danfestatable.domain.product.exception.ProductNotFoundException;
import com.dku.council.danfestatable.domain.product.model.dto.list.SummarizedProductDto;
import com.dku.council.danfestatable.domain.product.model.entity.Product;
import com.dku.council.danfestatable.domain.product.repository.ProductRepository;
import com.dku.council.danfestatable.domain.user.model.UserRole;
import com.dku.council.danfestatable.global.error.exception.NotGrantedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductAdminService {

    private final ProductRepository productRepository;

    @Transactional
    public void createProduct(RequestCreateProductDto dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .quantity(Integer.parseInt(dto.getQuantity()))
                .requiredHeart(Integer.parseInt(dto.getRequiredHeart()))
                .build();
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long id, RequestCreateProductDto dto) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.update(dto.getName(), dto.getDescription(), dto.getQuantity(), dto.getRequiredHeart());
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }
}
