package com.dku.council.danfestatable.domain.admin.controller;

import com.dku.council.danfestatable.domain.admin.dto.request.RequestCreateTableDto;
import com.dku.council.danfestatable.domain.admin.dto.list.SummarizedTableAdminDto;
import com.dku.council.danfestatable.domain.admin.dto.request.RequestIncreaseHeartDto;
import com.dku.council.danfestatable.domain.admin.service.MatchingTableAdminService;
import com.dku.council.danfestatable.global.auth.jwt.AppAuthentication;
import com.dku.council.danfestatable.global.auth.role.AdminAuth;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "매칭 테이블(관리자)", description = "매칭 테이블(관리자) 관련 API")
@RequestMapping("/admin/matching-table")
@RequiredArgsConstructor
public class MatchingTableAdminController {

    private final MatchingTableAdminService service;

    /**
     * 전체 테이블 목록 조회 (관리자용)
     * <p>관리자용 테이블 목록 조회 기능이라 사용자용 테이블 목록 조회랑 다른 필드를 가집니다.</p>
     */
    @GetMapping
    @AdminAuth
    public List<SummarizedTableAdminDto> list(AppAuthentication auth) {
        return service.list();
    }

    /**
     * 강제 테이블 생성
     */
    @PostMapping("/create")
    @AdminAuth
    public void createTable(AppAuthentication auth, @RequestBody RequestCreateTableDto dto) {
        service.create(dto);
    }

    /**
     * 테이블 시간 추가 (1시간)
     */
    @PatchMapping("/{tableId}/add-time")
    @AdminAuth
    public void addTime(AppAuthentication auth, @PathVariable Long tableId) {
        service.addTime(tableId);
    }

    /**
     * 테이블 삭제
     */
    @DeleteMapping("/{tableId}")
    @AdminAuth
    public void deleteTable(AppAuthentication auth, @PathVariable Long tableId) {
        service.delete(tableId);
    }

    /**
     * 수동 테이블 하트 관리
     * <p>테이블의 하트를 증가 또는 감소시킵니다.</p>
     * <p>isPlus가 true일 때, 하트를 증가시킵니다.</p>
     *
     * @param tableId        테이블 ID
     */
    @PatchMapping("/{tableId}/increase-heart")
    @AdminAuth
    public void increaseHeart(AppAuthentication auth, @PathVariable Long tableId, @RequestParam boolean isPlus,@RequestBody RequestIncreaseHeartDto dto) {
        service.increaseHeart(tableId, dto.getRequestHeart(), isPlus);
    }
}
