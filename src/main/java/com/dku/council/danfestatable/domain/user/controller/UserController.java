package com.dku.council.danfestatable.domain.user.controller;

import com.dku.council.danfestatable.domain.user.model.dto.response.ResponseLoginDto;
import com.dku.council.danfestatable.domain.user.model.dto.response.ResponseOAuthUrl;
import com.dku.council.danfestatable.domain.user.service.OAuthService;
import com.dku.council.danfestatable.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@Tag(name = "유저", description = "유저 관련 API")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final OAuthService oAuthService;

    /**
     * 카카오 로그인 URL 반환
     */
    @GetMapping("/oauth2/kakao")
    public ResponseOAuthUrl loginOauthKakao() {
        String url = oAuthService.getKakaoLoginUrl();
        return new ResponseOAuthUrl(url);
    }

    /**
     * 카카오 OAuth 계정 로그인
     */
    @GetMapping("/oauth2/code/kakao")
    public ResponseLoginDto socialLogin(@RequestParam String code) {
        return oAuthService.socialLogin(code);
    }
}
