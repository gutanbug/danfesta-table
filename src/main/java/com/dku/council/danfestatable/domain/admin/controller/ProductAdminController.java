package com.dku.council.danfestatable.domain.admin.controller;

import com.dku.council.danfestatable.domain.admin.dto.request.RequestCreateProductDto;
import com.dku.council.danfestatable.domain.admin.service.ProductAdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "상품(관리자)", description = "상품(관리자) 관련 API")
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductAdminService service;

    /**
     * 상품 추가
     */
    @PostMapping("/create")
    public void createProduct(@RequestBody RequestCreateProductDto dto) {
        service.createProduct(dto);
    }

    /**
     * 상품 수정
     */
    @PatchMapping("/{productId}")
    public void updateProduct(@PathVariable Long productId, @RequestBody RequestCreateProductDto dto) {
        service.updateProduct(productId, dto);
    }

    /**
     * 상품 삭제
     */
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        service.deleteProduct(productId);
    }
}
