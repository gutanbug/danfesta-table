package com.dku.council.danfestatable.domain.order.controller;

import com.dku.council.danfestatable.domain.order.model.dto.list.SummarizedOrderDto;
import com.dku.council.danfestatable.domain.order.model.dto.request.RequestCreateOrderDto;
import com.dku.council.danfestatable.domain.order.service.OrderService;
import com.dku.council.danfestatable.global.auth.jwt.AppAuthentication;
import com.dku.council.danfestatable.global.auth.role.UserAuth;
import com.dku.council.danfestatable.global.model.dto.ResponseIdDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "주문", description = "주문 관련 API")
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 내 테이블 전체 상품 주문 조회
     */
    @GetMapping
    @UserAuth
    public List<SummarizedOrderDto> list(AppAuthentication auth) {
        return orderService.list(auth.getUserId());
    }

    /**
     * 상품 주문 생성
     * @return   주문 ID
     */
    @PostMapping
    @UserAuth
    public ResponseIdDto createOrder(AppAuthentication auth,
                                     @RequestParam("productId") Long productId,
                                     @RequestBody RequestCreateOrderDto dto) {
        Long orderId = orderService.createOrder(auth.getUserId(), productId, dto);
        return new ResponseIdDto(orderId);
    }

    /**
     * 상품 주문 취소
     */
    @DeleteMapping("/{orderId}")
    @UserAuth
    public void cancelOrder(AppAuthentication auth, @PathVariable Long orderId) {
        orderService.cancelOrder(auth.getUserId(), orderId);
    }
}
