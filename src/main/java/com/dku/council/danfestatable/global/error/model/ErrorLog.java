package com.dku.council.danfestatable.global.error.model;

import com.dku.council.danfestatable.global.error.model.dto.ErrorResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorLog {
    public final ErrorResponseDto info;
    public final String errorLog;


}
