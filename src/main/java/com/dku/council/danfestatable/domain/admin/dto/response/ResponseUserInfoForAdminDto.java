package com.dku.council.danfestatable.domain.admin.dto.response;

import com.dku.council.danfestatable.domain.user.model.entity.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseUserInfoForAdminDto {
    private final String name;
    private final String gender;
    private final String phone;

    public ResponseUserInfoForAdminDto(User user) {
        this.name = blindName(user.getName());
        this.gender = user.getGender();
        this.phone = user.getPhone();
    }

    private String blindName(String name) {
        if(name.length() == 3) {
            return name.charAt(0) + "*" + name.substring(2);
        } else if(name.length() == 2) {
            return name.charAt(0) + "*";
        } else if(name.length() == 4) {
            return name.charAt(0) + "**" + name.substring(3);
        }
        return null;
    }

    public static List<ResponseUserInfoForAdminDto> of(List<User> users) {
        return users.stream()
                .map(ResponseUserInfoForAdminDto::new)
                .collect(Collectors.toList());
    }
}
