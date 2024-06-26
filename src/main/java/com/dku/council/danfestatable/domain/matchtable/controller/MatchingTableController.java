package com.dku.council.danfestatable.domain.matchtable.controller;

import com.dku.council.danfestatable.domain.matchtable.model.dto.response.ResponseMyTableDto;
import com.dku.council.danfestatable.domain.matchtable.model.dto.response.SummarizedTableDto;
import com.dku.council.danfestatable.domain.matchtable.service.MatchingTableService;
import com.dku.council.danfestatable.global.auth.jwt.AppAuthentication;
import com.dku.council.danfestatable.global.auth.role.UserAuth;
import com.dku.council.danfestatable.global.model.dto.ResponseIdDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matching-table")
@Tag(name = "매칭 테이블", description = "매칭 테이블 관련 API")
@RequiredArgsConstructor
public class MatchingTableController {

    private final MatchingTableService matchingTableService;

    /**
     * 테이블 참가 신청
     * @param auth     사용자 인증 정보
     * @param number   테이블 번호
     */
    @UserAuth
    @PostMapping("/{number}")
    public void joinTable(AppAuthentication auth, @PathVariable int number) {
        matchingTableService.joinTable(auth.getUserId(), number);
    }

    /**
     * 전체 테이블 목록 조회
     */
    @GetMapping
    public List<SummarizedTableDto> list() {
        return matchingTableService.list();
    }

    /**
     * 내 테이블 조회
     * <p>현재 내가 있는 테이블의 정보를 보여줍니다.</p>
     */
    @GetMapping("/my")
    @UserAuth
    public ResponseMyTableDto getMyTable(AppAuthentication auth) {
        return matchingTableService.getMyTable(auth.getUserId());
    }
}
