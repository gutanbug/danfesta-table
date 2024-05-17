package com.dku.council.danfestatable.domain.user.service;

import com.dku.council.danfestatable.domain.user.exception.UserNotFoundException;
import com.dku.council.danfestatable.domain.user.model.dto.response.ResponseLoginDto;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import com.dku.council.danfestatable.domain.user.repository.UserRepository;
import com.dku.council.danfestatable.global.auth.jwt.AuthenticationToken;
import com.dku.council.danfestatable.global.auth.jwt.JwtProvider;
import com.dku.council.danfestatable.infra.nhn.global.model.dto.response.ResponseToken;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthService {

    private final Environment env;
    private final RestTemplate restTemplate = new RestTemplate();
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public String getKakaoLoginUrl() {
        String loginUri = env.getProperty("app.kakao.login-uri");
        String clientId = env.getProperty("app.kakao.client-id");
        String redirectUri = env.getProperty("app.kakao.redirect-uri");
        return String.format(loginUri, clientId, redirectUri);
    }

    @Transactional
    public ResponseLoginDto socialLogin(String code) {
        String accessToken = getAccessToken(code);
        JsonNode userResource = getUserResource(accessToken);

        String email = findEmail(userResource);
        System.out.println("email = " + email);
        User user;

        if(userRepository.findUserByLoginId(email).isEmpty()) {
            user = makeUserFromResource(userResource);
        } else {
            user = userRepository.findUserByLoginId(email).orElseThrow(UserNotFoundException::new);
        }

        AuthenticationToken token = jwtProvider.issue(user);
        return new ResponseLoginDto(token);
    }

    private User makeUserFromResource(JsonNode userResource) {
        User user = User.builder()
                .loginId(userResource.get("kakao_account").get("email").asText())
                .name(userResource.get("kakao_account").get("name").asText())
                .gender(checkGender(userResource.get("kakao_account").get("gender").asText()))
                .phone(changePhone(userResource.get("kakao_account").get("phone_number").asText()))
                .password(UUID.randomUUID().toString())
                .build();
        user = userRepository.save(user);
        return user;
    }

    private String changePhone(String phone) {
        String[] split = phone.split(" ");
        String result = "0" + split[1];
        return eliminateDash(result);
    }

    private String checkGender(String gender) {
        switch (gender) {
            case "male" :
                return "남자";
            case "female" :
                return "여자";
            default:
                return null;
        }
    }

    private String findEmail(JsonNode userResource) {
        return userResource.get("kakao_account").get("email").asText();
    }

    private String getAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        String clientId = env.getProperty("app.kakao.client-id");
        String clientSecret = env.getProperty("app.kakao.client-secret");
        String redirectUri = env.getProperty("app.kakao.redirect-uri");
        String tokenUri = env.getProperty("app.kakao.token-uri");

        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(tokenUri, HttpMethod.POST, entity, JsonNode.class);
        return response.getBody().get("access_token").asText();
    }

    private JsonNode getUserResource(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);

        String resourceUri = env.getProperty("app.kakao.resource-uri");
        return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }

    private String eliminateDash(String phoneNumber) {
        return phoneNumber.replaceAll("-", "");
    }
}
