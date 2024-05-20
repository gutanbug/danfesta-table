package com.dku.council.danfestatable.domain.admin.controller;

import com.dku.council.danfestatable.domain.waiting.model.WaitingStatus;
import com.dku.council.danfestatable.domain.waiting.model.dto.response.ResponseWaitingDto;
import com.dku.council.danfestatable.domain.waiting.service.WaitingService;
import com.dku.council.danfestatable.global.auth.jwt.AppAuthentication;
import com.dku.council.danfestatable.global.auth.role.AdminAuth;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "테이블 입장 대기 관리", description = "테이블 입장 대기 관리 API")
@RequiredArgsConstructor
@RequestMapping("/admin/waiting")
public class WaitingController {

    private final WaitingService waitingService;

    /**
     * 테이블 입장 대기 목록 조회
     *
     * @param status   대기 상태
     */
    @GetMapping
    @AdminAuth
    public List<ResponseWaitingDto> list(AppAuthentication auth,
                                         @RequestParam WaitingStatus status) {
        return waitingService.list(status);
    }

    /**
     * 테이블 입장 승인
     */
    @PatchMapping("/{waitingId}/approval")
    @AdminAuth
    public void approval(AppAuthentication auth,
                         @PathVariable Long waitingId) {
        waitingService.approveWaiting(waitingId);
    }

    /**
     * 테이블 입장 거절
     */
    @PatchMapping("/{waitingId}/reject")
    @AdminAuth
    public void reject(AppAuthentication auth,
                       @PathVariable Long waitingId) {
        waitingService.rejectWaiting(waitingId);
    }
}
