package com.dku.council.danfestatable.infra.nhn.sms.service;

import com.dku.council.danfestatable.domain.user.exception.AlreadyUserExistException;
import com.dku.council.danfestatable.domain.user.exception.NotSMSSentException;
import com.dku.council.danfestatable.domain.user.exception.WrongSMSCodeException;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import com.dku.council.danfestatable.domain.user.repository.SignupAuthRepository;
import com.dku.council.danfestatable.domain.user.repository.UserRepository;
import com.dku.council.danfestatable.domain.user.util.CodeGenerator;
import com.dku.council.danfestatable.infra.nhn.sms.model.SMSAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SMSVerificationService {

    public static final String SMS_AUTH_NAME = "sms";
    public static final String SMS_AUTH_COMPLETE_SIGN = "OK";

    private final Clock clock;
    private final MessageSource messageSource;
    private final SMSService smsService;
    private final UserRepository userRepository;
    private final SignupAuthRepository smsAuthRepository;

    @Value("${app.auth.sms.digit-count}")
    private final int digitCount;

    @Transactional
    public void sendSMSCode(String phone) {
        checkAlreadyPhone(phone);

        String code = CodeGenerator.generateDigitCode(digitCount);
        phone = phone.trim().replaceAll("-", "");

        Instant now = Instant.now(clock);
        smsAuthRepository.setAuthPayload(phone, SMS_AUTH_NAME, new SMSAuth(phone, code), now);

        Locale locale = LocaleContextHolder.getLocale();
        smsService.sendSMS(phone, messageSource.getMessage("sms.auth-message", new Object[]{code}, locale));
    }

    private void checkAlreadyPhone(String phone) {
        Optional<User> alreadyUser = userRepository.findByPhone(phone);
        if (alreadyUser.isPresent()) {
            throw new AlreadyUserExistException();
        }
    }

    public void verifySMSCode(String phone, String code) {
        Instant now = Instant.now(clock);
        SMSAuth authObj = smsAuthRepository.getAuthPayload(phone, SMS_AUTH_NAME, SMSAuth.class, now)
                .orElseThrow(NotSMSSentException::new);

        if (!authObj.getCode().equals(code.trim())) {
            throw new WrongSMSCodeException();
        }

        SMSAuth newAuthObj = new SMSAuth(authObj.getPhone(), SMS_AUTH_COMPLETE_SIGN);
        smsAuthRepository.setAuthPayload(phone, SMS_AUTH_NAME, newAuthObj, now);
    }

    public boolean deleteSMSAuth(String phone) {
        return smsAuthRepository.deleteAuthPayload(phone, SMS_AUTH_NAME);
    }
}
