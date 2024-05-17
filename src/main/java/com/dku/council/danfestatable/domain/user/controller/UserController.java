package com.dku.council.danfestatable.domain.user.controller;

import com.dku.council.danfestatable.domain.user.model.dto.request.*;
import com.dku.council.danfestatable.domain.user.model.dto.response.ResponseLoginDto;
import com.dku.council.danfestatable.domain.user.model.dto.response.ResponseOAuthUrl;
import com.dku.council.danfestatable.domain.user.service.OAuthService;
import com.dku.council.danfestatable.domain.user.service.UserService;
import com.dku.council.danfestatable.global.auth.jwt.AppAuthentication;
import com.dku.council.danfestatable.global.auth.role.UserAuth;
import com.dku.council.danfestatable.infra.nhn.sms.service.SMSVerificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "유저", description = "유저 관련 API")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final SMSVerificationService smsVerificationService;
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

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseLoginDto login(@Valid @RequestBody RequestLoginDto dto) {
        return userService.login(dto);
    }

    /**
     * 회원가입
     * <p>성별(gender) : "남자" or "여자"</p>
     * <p>회원가입 SMS 코드 전송 -> SMS 코드 확인 -> 회원가입 순으로 진행되어야 합니다.</p>
     *
     */
    @PostMapping("/signup")
    public void signup(@Valid @RequestBody RequestSignupDto dto) {
        userService.signUp(dto);
    }

    /**
     * 회원가입 SMS 코드 전송
     */
    @PostMapping("/signup/sms")
    public void sendVerificationSMS(@Valid @RequestBody RequestWithPhoneDto dto) {
        smsVerificationService.sendSMSCode(dto.getPhone());
    }

    /**
     * 회원가입 SMS 코드 확인
     */
    @PostMapping("signup/sms/verify")
    public void verifySMSCode(@Valid @RequestBody RequestVerifySMSCodeDto dto) {
        smsVerificationService.verifySMSCode(dto.getPhoneNumber(), dto.getCode());
    }

    /**
     * 직원 호출
     * <p>관리자는 한 명만 있어야 api 호출이 가능합니다.</p>
     */
    @UserAuth
    @PostMapping("/staff")
    public void callStaff(AppAuthentication auth,  @RequestBody RequestCallStaffDto dto) {
        userService.callStaff(auth.getUserId(), dto);
    }
}
