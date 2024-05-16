package com.dku.council.danfestatable.domain.message.controller;

import com.dku.council.danfestatable.domain.message.model.dto.request.RequestCreateMessageDto;
import com.dku.council.danfestatable.domain.message.service.MessageService;
import com.dku.council.danfestatable.global.auth.jwt.AppAuthentication;
import com.dku.council.danfestatable.global.auth.role.UserAuth;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/message")
@Tag(name = "메시지", description = "메시지 관련 API")
public class MessageController {

    private final MessageService messageService;

    /**
     * 특정 테이블로 하트와 메시지 전송
     *
     * @param tableId   전송할 테이블 Id
     */
    @UserAuth
    @PostMapping("/{tableId}")
    public void sendHeartAndMessage(AppAuthentication auth,
                                    @PathVariable Long tableId,
                                    @RequestBody RequestCreateMessageDto dto) {
        messageService.sendMessageAndHeart(auth.getUserId(), tableId, dto);
    }

}
