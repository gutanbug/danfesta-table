package com.dku.council.danfestatable.domain.user.repository;

import java.time.Instant;
import java.util.Optional;

public interface SignupAuthRepository {

    void setAuthPayload(String phone, String authName, Object data, Instant now);

    <T> Optional<T> getAuthPayload(String phone, String authName, Class<T> payloadClass, Instant now);

    boolean deleteAuthPayload(String phone, String authName);
}
