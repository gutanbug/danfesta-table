package com.dku.council.danfestatable.domain.user.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestSignupDto {

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phone;

    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @Schema(description = "성별", example = "남자")
    private String gender;

    public RequestSignupDto(String name, String phone, String password, String gender) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
    }
}
