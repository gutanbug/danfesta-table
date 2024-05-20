package com.dku.council.danfestatable.domain.user.service;

import com.dku.council.danfestatable.domain.matchtable.exception.MatchingTableNotFoundException;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.matchtable.repository.MatchingTableRepository;
import com.dku.council.danfestatable.domain.user.exception.AlreadyUserExistException;
import com.dku.council.danfestatable.domain.user.exception.MatchingTableNotEnteredException;
import com.dku.council.danfestatable.domain.user.exception.UserNotFoundException;
import com.dku.council.danfestatable.domain.user.model.Enrolled;
import com.dku.council.danfestatable.domain.user.model.dto.request.RequestCallStaffDto;
import com.dku.council.danfestatable.domain.user.model.dto.request.RequestLoginDto;
import com.dku.council.danfestatable.domain.user.model.dto.request.RequestSignupDto;
import com.dku.council.danfestatable.domain.user.model.dto.response.ResponseLoginDto;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import com.dku.council.danfestatable.domain.user.repository.UserRepository;
import com.dku.council.danfestatable.global.auth.jwt.AuthenticationToken;
import com.dku.council.danfestatable.global.auth.jwt.JwtProvider;
import com.dku.council.danfestatable.infra.nhn.sms.service.MMSService;
import com.dku.council.danfestatable.infra.nhn.sms.service.SMSVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private static final String MMSTitle = "[직원호출]";

    private final UserRepository userRepository;
    private final MatchingTableRepository tableRepository;
    private final PasswordEncoder passwordEncoder;
    private final SMSVerificationService smsVerificationService;
    private final MMSService smsService;
    private final JwtProvider jwtProvider;
    private final MessageSource messageSource;

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
                .loginId(UUID.randomUUID().toString())
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
        if (userRepository.findByPhone(dto.getPhone()).isPresent()) {
            throw new AlreadyUserExistException();
        }
    }

    public ResponseLoginDto login(RequestLoginDto dto) {
        String phone = dto.getPhoneNumber().trim().replaceAll("-", "");
        User user = userRepository.findByPhone(phone).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new UserNotFoundException();
        }

        AuthenticationToken tokenResponse = jwtProvider.issue(user);
        return new ResponseLoginDto(tokenResponse, user);
    }

    public void callStaff(Long userId, RequestCallStaffDto dto) {
        MatchingTable table = tableRepository.findByUserId(userId).orElseThrow(MatchingTableNotEnteredException::new);
        User adminUser = userRepository.findAll().stream()
                .filter(u -> u.getUserRole().isAdmin()).findFirst().orElseThrow(UserNotFoundException::new);
        smsService.sendMMS(MMSTitle, adminUser.getPhone(), messageSource.getMessage("mms.call-staff",
                new Object[]{table.getTableNumber(), dto.getMessage()}, LocaleContextHolder.getLocale()));
    }
}
