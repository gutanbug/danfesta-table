package com.dku.council.danfestatable.domain.product.service;

import com.dku.council.danfestatable.domain.product.model.dto.list.SummarizedProductDto;
import com.dku.council.danfestatable.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<SummarizedProductDto> list() {
        return productRepository.findAll()
                .stream().map(SummarizedProductDto::new)
                .collect(Collectors.toList());
    }
}
