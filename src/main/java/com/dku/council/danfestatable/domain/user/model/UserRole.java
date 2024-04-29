package com.dku.council.danfestatable.domain.user.model;

import com.dku.council.danfestatable.global.auth.jwt.AppAuthentication;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.dku.council.danfestatable.global.auth.role.UserAuthNames.*;

@Getter
public enum UserRole {
    USER(ROLE_USER),
    ADMIN(combine(ROLE_ADMIN, ROLE_USER));

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    private static final Map<String, UserRole> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(UserRole::getName, e -> e));

    public static UserRole of(String name) {return BY_LABEL.get(name);}

    public static UserRole from(AppAuthentication auth) {
        return auth.getUserRole();
    }

    public boolean isAdmin() {
        return this == ADMIN;
    }
}
