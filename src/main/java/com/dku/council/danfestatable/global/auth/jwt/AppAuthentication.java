package com.dku.council.danfestatable.global.auth.jwt;

import com.dku.council.danfestatable.domain.user.model.UserRole;
import org.springframework.security.core.Authentication;

public interface AppAuthentication extends Authentication {

    Long getUserId();

    UserRole getUserRole();

    boolean isAdmin();
}
