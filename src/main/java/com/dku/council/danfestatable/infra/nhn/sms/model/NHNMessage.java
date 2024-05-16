package com.dku.council.danfestatable.infra.nhn.sms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class NHNMessage {

    private String senderAddress;
    private String senderName;
    private String title;
    private String body;
    private Receiver receiver;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Receiver{
        private String receiveMailAddr;
    }
}
