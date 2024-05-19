package com.dku.council.danfestatable.domain.admin.controller;

import com.dku.council.danfestatable.domain.admin.service.HeartPaymentAdminService;
import com.dku.council.danfestatable.domain.heart_payment.model.PaymentStatus;
import com.dku.council.danfestatable.domain.heart_payment.model.dto.list.SummarizedHeartPaymentDto;
import com.dku.council.danfestatable.global.auth.jwt.AppAuthentication;
import com.dku.council.danfestatable.global.auth.role.AdminAuth;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/heart-payment")
@Tag(name = "하트 결제(관리자)", description = "하트 결제(관리자) 관련 API")
@Slf4j
public class HeartPaymentAdminController {

    private final HeartPaymentAdminService service;

    /**
     * 결제 상태에 따른 하트 결제 요청 리스트 조회
     *
     * @param status   결제 상태
     */
    @AdminAuth
    @GetMapping
    public List<SummarizedHeartPaymentDto> listWithStatus(@RequestParam PaymentStatus status,
                                                          AppAuthentication auth) {
        return service.listWithStatus(status);
    }

    /**
     * 하트 결제 요청 승인
     *
     * @param heartPaymentId   승인할 하트 결제 요청 Id
     */
    @AdminAuth
    @PatchMapping("/{heartPaymentId}/approve")
    public void approveHeartPayment(@PathVariable Long heartPaymentId) {
        service.approveHeartPayment(heartPaymentId);
    }

/**
     * 하트 결제 요청 거절
     *
     * @param heartPaymentId   거절할 하트 결제 요청 Id
     */
    @AdminAuth
    @PatchMapping("/{heartPaymentId}/reject")
    public void rejectHeartPayment(@PathVariable Long heartPaymentId) {
        service.rejectHeartPayment(heartPaymentId);
    }
}
