package com.dku.council.danfestatable.infra.nhn.global.service;

import com.dku.council.danfestatable.infra.nhn.global.exception.CannotGetTokenException;
import com.dku.council.danfestatable.infra.nhn.global.exception.NotInitializedException;
import com.dku.council.danfestatable.infra.nhn.global.model.dto.request.RequestToken;
import com.dku.council.danfestatable.infra.nhn.global.model.dto.response.ResponseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NHNAuthService {

    private RequestToken tokenRequest;

    private final WebClient webClient;

    @Value("${nhn.auth.api-path}")
    private String apiPath;

    @Value("${nhn.auth.tenant-id}")
    private String tenantId;

    @Value("${nhn.auth.username}")
    private String username;

    @Value("${nhn.auth.password}")
    private String password;

    @PostConstruct
    private void intialize() {
        RequestToken.PasswordCredentials pwdCred = new RequestToken.PasswordCredentials(username, password);
        RequestToken.Auth auth = new RequestToken.Auth(tenantId, pwdCred);
        tokenRequest = new RequestToken(auth);
    }

    public String requestToken() {
        if (tokenRequest == null) {
            throw new NotInitializedException();
        }

        ResponseToken response;

        try {
            response = webClient.post()
                    .uri(apiPath)
                    .header("Content-Type", "application/json")
                    .body(Mono.just(tokenRequest), RequestToken.class)
                    .retrieve()
                    .bodyToMono(ResponseToken.class)
                    .block();
        } catch (Throwable e) {
            throw new CannotGetTokenException(e);
        }

        String token = Optional.ofNullable(response)
                .map(ResponseToken::getAccess)
                .map(ResponseToken.Access::getToken)
                .map(ResponseToken.Token::getId)
                .orElse(null);

        if (token == null) {
            throw new CannotGetTokenException();
        }

        return token;
    }
}
