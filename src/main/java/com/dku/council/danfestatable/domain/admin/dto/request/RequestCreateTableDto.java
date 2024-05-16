package com.dku.council.danfestatable.domain.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCreateTableDto {
    @Schema(description = "테이블 번호", example = "1")
    private int tableNumber;

    public RequestCreateTableDto(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
