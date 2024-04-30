package com.dku.council.danfestatable.domain.admin.service;

import com.dku.council.danfestatable.domain.admin.dto.request.RequestCreateProductDto;
import com.dku.council.danfestatable.domain.product.exception.ProductNotFoundException;
import com.dku.council.danfestatable.domain.product.model.dto.list.SummarizedProductDto;
import com.dku.council.danfestatable.domain.product.model.entity.Product;
import com.dku.council.danfestatable.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductPageService {

    private final ProductRepository productRepository;

    public List<SummarizedProductDto> getProducts() {
        return productRepository.findAll()
                .stream().map(SummarizedProductDto::new)
                .collect(Collectors.toList());
    }

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
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }
}
