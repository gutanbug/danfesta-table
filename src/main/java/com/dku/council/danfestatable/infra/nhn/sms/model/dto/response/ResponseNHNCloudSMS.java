package com.dku.council.danfestatable.infra.nhn.sms.model.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseNHNCloudSMS {
    private final Header header;
    private final Body body;

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Header {
        private final boolean isSuccessful;
        private final int resultCode;
        private final String resultMessage;

        public boolean getIsSuccessful() {
            return isSuccessful;
        }
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Body {
        private final Data data;

        @Getter
        @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
        public static class Data {
            private final String requestId;
            private final String statusCode;
            private final List<SendResult> sendResultList;

            @Getter
            @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
            public static class SendResult {
                private final String recipientNo;
                private final int resultCode;
                private final String resultMessage;
            }
        }
    }
}
