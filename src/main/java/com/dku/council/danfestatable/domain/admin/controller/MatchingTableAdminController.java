package com.dku.council.danfestatable.domain.admin.controller;

import com.dku.council.danfestatable.domain.admin.dto.request.RequestCreateTableDto;
import com.dku.council.danfestatable.domain.admin.dto.list.SummarizedTableAdminDto;
import com.dku.council.danfestatable.domain.admin.service.MatchingTableAdminService;
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
    public List<SummarizedTableAdminDto> list() {
        return service.list();
    }

    /**
     * 강제 테이블 생성
     */
    @PostMapping("/create")
    public void createTable(@RequestBody RequestCreateTableDto dto) {
        service.create(dto);
    }

    /**
     * 테이블 시간 추가 (1시간)
     */
    @PatchMapping("/{tableId}/add-time")
    public void addTime(@PathVariable Long tableId) {
        service.addTime(tableId);
    }

    /**
     * 테이블 삭제
     */
    @DeleteMapping("/{tableId}")
    public void deleteTable(@PathVariable Long tableId) {
        service.delete(tableId);
    }

}
