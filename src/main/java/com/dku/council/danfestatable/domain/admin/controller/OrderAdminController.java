package com.dku.council.danfestatable.domain.admin.controller;

import com.dku.council.danfestatable.domain.admin.dto.list.SummarizedOrderForAdminDto;
import com.dku.council.danfestatable.domain.admin.service.OrderAdminService;
import com.dku.council.danfestatable.global.auth.jwt.AppAuthentication;
import com.dku.council.danfestatable.global.auth.role.AdminAuth;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "주문(관리자)", description = "주문(관리자) API")
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class OrderAdminController {

    private final OrderAdminService service;

    /**
     * 전체 상품 주문 조회 (관리자용)
     */
    @GetMapping
    @AdminAuth
    public List<SummarizedOrderForAdminDto> listOrder(AppAuthentication auth) {
        return service.list();
    }

    /**
     * 상품 주문 승인
     *
     * @param orderId   주문 ID
     */
    @PostMapping("/approval/{orderId}")
    @AdminAuth
    public void approvalOrder(AppAuthentication auth, @PathVariable Long orderId) {
        service.approvalOrder(orderId);
    }

    /**
     * 상품 주문 거절
     *
     * @param orderId   주문 ID
     */
    @PostMapping("/reject/{orderId}")
    @AdminAuth
    public void rejectOrder(AppAuthentication auth, @PathVariable Long orderId) {
        service.rejectOrder(orderId);
    }
}
