package com.dku.council.danfestatable.domain.product.controller;

import com.dku.council.danfestatable.domain.product.model.dto.list.SummarizedProductDto;
import com.dku.council.danfestatable.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/products")
@Tag(name = "상품", description = "상품 관련 API")
public class ProductController {

    private final ProductService productService;

    /**
     * 전체 목록 조회
     */
    @GetMapping
    public List<SummarizedProductDto> list() {
        return productService.list();
    }
}
