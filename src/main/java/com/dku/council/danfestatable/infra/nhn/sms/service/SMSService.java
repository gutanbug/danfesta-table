package com.dku.council.danfestatable.infra.nhn.sms.service;

import com.dku.council.danfestatable.infra.nhn.global.exception.CannotSendSMSException;
import com.dku.council.danfestatable.infra.nhn.global.exception.UnexpectedResponseException;
import com.dku.council.danfestatable.infra.nhn.sms.model.dto.response.ResponseNHNCloudSMS;
import com.dku.council.danfestatable.infra.nhn.sms.model.dto.request.RequestNHNCloudSMS;
import com.dku.council.danfestatable.infra.nhn.sms.model.dto.response.ResponseNHNCloudSMS.Body.Data.SendResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class SMSService {

    private final WebClient webClient;

    @Value("${nhn.sms.api-path}")
    private String apiPath;

    @Value("${nhn.sms.secret-key}")
    private String secretKey;

    @Value("${nhn.sms.sender-phone}")
    private String senderPhone;

    public void sendSMS(String phone, String body) {
        RequestNHNCloudSMS request = new RequestNHNCloudSMS(senderPhone, phone, body);

        ResponseNHNCloudSMS response = null;

        try {
            response = webClient.post()
                    .uri(apiPath)
                    .header("X-Secret-Key", secretKey)
                    .header("Content-Type", "application/json")
                    .body(Mono.just(request), RequestNHNCloudSMS.class)
                    .retrieve()
                    .bodyToMono(ResponseNHNCloudSMS.class)
                    .block();

            validateResponse(response);
        } catch (WebClientResponseException e) {
            throw new CannotSendSMSException(e);
        } catch (Throwable e) {
            if (response != null) {
                log.debug(String.format("Error while sending SMS to %s: %s", phone, response));
                log.debug(response.toString());
            }
            throw new CannotSendSMSException(e);
        }
    }

    private static void validateResponse(ResponseNHNCloudSMS response) {
        if (response == null || response.getHeader() == null || response.getBody() == null) {
            throw new UnexpectedResponseException("Response is incorrect");
        } else {
            ResponseNHNCloudSMS.Header header = response.getHeader();
            if (!header.getIsSuccessful()) {
                throw new UnexpectedResponseException(header.getResultMessage());
            }

            List<SendResult> results = Optional.of(response)
                    .map(ResponseNHNCloudSMS::getBody)
                    .map(ResponseNHNCloudSMS.Body::getData)
                    .map(ResponseNHNCloudSMS.Body.Data::getSendResultList)
                    .orElse(null);

            if (results == null) {
                throw new UnexpectedResponseException("Invalid body");
            } else {
                for (SendResult result : results) {
                    if (result.getResultCode() != 0) {
                        throw new UnexpectedResponseException(result.getResultMessage());
                    }
                }
            }
        }
    }
}
