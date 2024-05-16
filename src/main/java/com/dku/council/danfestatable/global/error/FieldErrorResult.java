package com.dku.council.danfestatable.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FieldErrorResult {
    private final String name;
    private final String error;
}
