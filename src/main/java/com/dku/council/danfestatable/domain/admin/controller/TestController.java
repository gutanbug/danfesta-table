package com.dku.council.danfestatable.domain.admin.controller;

import com.dku.council.danfestatable.domain.matchtable.exception.MatchingTableNotFoundException;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.matchtable.repository.MatchingTableRepository;
import com.dku.council.danfestatable.domain.user.exception.UserNotFoundException;
import com.dku.council.danfestatable.domain.user.model.Enrolled;
import com.dku.council.danfestatable.domain.user.model.dto.response.ResponseLoginDto;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import com.dku.council.danfestatable.domain.user.repository.UserRepository;
import com.dku.council.danfestatable.global.auth.jwt.AuthenticationToken;
import com.dku.council.danfestatable.global.auth.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "테스트", description = "테스트 관련 API")
@RequestMapping("/test")
public class TestController {

    private final UserRepository userRepository;
    private final MatchingTableRepository matchingTableRepo;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * 임시 로그인
     */
    @PostMapping("/login")
    @Transactional
    public ResponseLoginDto login(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(UserNotFoundException::new);
        AuthenticationToken token = jwtProvider.issue(user);
        return new ResponseLoginDto(token, user);
    }

    /**
     * 강제 회원가입
     */
    @PostMapping("/sign-up")
    @Transactional
    public void signup(@RequestParam String name,
                       @RequestParam String phone,
                       @RequestParam String gender,
                       @RequestParam String loginId,
                       @RequestParam String password,
                       @RequestParam Enrolled enrolled,
                       @RequestParam (defaultValue = "false") boolean setAdmin) {
        User user = User.builder()
                .name(name)
                .phone(phone)
                .gender(gender)
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .enrolled(enrolled)
                .build();

        if (setAdmin) {
            user.changeToAdmin();
        }
        userRepository.save(user);
    }
}
