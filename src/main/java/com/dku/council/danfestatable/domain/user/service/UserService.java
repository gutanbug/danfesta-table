package com.dku.council.danfestatable.domain.user.service;

import com.dku.council.danfestatable.domain.user.exception.AlreadyUserExistException;
import com.dku.council.danfestatable.domain.user.exception.UserNotFoundException;
import com.dku.council.danfestatable.domain.user.model.Enrolled;
import com.dku.council.danfestatable.domain.user.model.dto.request.RequestLoginDto;
import com.dku.council.danfestatable.domain.user.model.dto.request.RequestSignupDto;
import com.dku.council.danfestatable.domain.user.model.dto.response.ResponseLoginDto;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import com.dku.council.danfestatable.domain.user.repository.UserRepository;
import com.dku.council.danfestatable.global.auth.jwt.AuthenticationToken;
import com.dku.council.danfestatable.global.auth.jwt.JwtProvider;
import com.dku.council.danfestatable.infra.nhn.sms.service.SMSVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SMSVerificationService smsVerificationService;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(RequestSignupDto dto) {
        String phone = eliminate(dto.getPhone());
        checkLoginId(dto);
        checkPhone(phone);

        User user = User.builder()
                .name(dto.getName())
                .phone(phone)
                .password(passwordEncoder.encode(dto.getPassword()))
                .gender(dto.getGender())
                .loginId(dto.getLoginId())
                .enrolled(Enrolled.FORM)
                .build();
        userRepository.save(user);
        smsVerificationService.deleteSMSAuth(dto.getPhone());
    }

    private String eliminate(String phone) {
        return phone.replaceAll("-", "");
    }

    private void checkPhone(String phone) {
        if (userRepository.findByPhone(phone).isPresent()) {
            throw new AlreadyUserExistException();
        }
    }

    private void checkLoginId(RequestSignupDto dto) {
        if (userRepository.findByLoginId(dto.getLoginId()).isPresent()) {
            throw new AlreadyUserExistException();
        }
    }

    public ResponseLoginDto login(RequestLoginDto dto) {
        User user = userRepository.findByLoginId(dto.getLoginId()).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new UserNotFoundException();
        }

        AuthenticationToken tokenResponse = jwtProvider.issue(user);
        return new ResponseLoginDto(tokenResponse);
    }
}
