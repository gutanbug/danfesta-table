package com.dku.council.danfestatable.domain.heart_payment.controller;

import com.dku.council.danfestatable.domain.heart_payment.model.dto.request.RequestCreateHeartPaymentDto;
import com.dku.council.danfestatable.domain.heart_payment.service.HeartPaymentService;
import com.dku.council.danfestatable.global.auth.jwt.AppAuthentication;
import com.dku.council.danfestatable.global.auth.role.UserAuth;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/heart-payment")
@Tag(name = "하트 결제", description = "하트 결제 관련 API")
@RequiredArgsConstructor
public class HeartPaymentController {

    private final HeartPaymentService service;

    /**
     * 하트 결제 주문 생성
     *
     * @param dto      하트 결제 생성 요청 DTO
     */
    @UserAuth
    @PostMapping
    public void createHeartPayment(AppAuthentication auth, @RequestBody RequestCreateHeartPaymentDto dto) {
        service.createHeartPayment(auth.getUserId(), dto);
    }
}
