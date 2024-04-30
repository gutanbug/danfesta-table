package com.dku.council.danfestatable.global.error.exception;

import com.dku.council.danfestatable.global.error.service.ErrorLogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ControllerAdvisor {

    private final MessageSource messageSource;
    private final ErrorLogService errorLogService;
}
