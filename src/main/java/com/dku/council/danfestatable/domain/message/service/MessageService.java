package com.dku.council.danfestatable.domain.message.service;

import com.dku.council.danfestatable.domain.matchtable.exception.MatchingTableNotFoundException;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.matchtable.repository.MatchingTableRepository;
import com.dku.council.danfestatable.domain.message.exception.CannotSendHeartToMyself;
import com.dku.council.danfestatable.domain.message.model.dto.request.RequestCreateMessageDto;
import com.dku.council.danfestatable.domain.message.model.entity.Message;
import com.dku.council.danfestatable.domain.message.repository.MessageRepository;
import com.dku.council.danfestatable.domain.order.exception.NotEnoughHeartException;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import com.dku.council.danfestatable.infra.nhn.sms.service.SMSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MessageService {

    private final MessageRepository repository;
    private final MatchingTableRepository tableRepository;
    private final SMSService smsService;
    private final MessageSource messageSource;

    @Transactional
    public void sendMessageAndHeart(Long userId, Long receiverTableId, RequestCreateMessageDto dto) {
        MatchingTable senderTable = tableRepository.findByUserId(userId)
                .orElseThrow(MatchingTableNotFoundException::new);

        MatchingTable receiverTable = tableRepository.findByIdWithActive(receiverTableId)
                .orElseThrow(MatchingTableNotFoundException::new);

        if (senderTable.getId().equals(receiverTable.getId())) {
            throw new CannotSendHeartToMyself();
        }
        if (senderTable.getTotalHeart() < dto.getSendHeartCount()) {
            throw new NotEnoughHeartException();
        }
        Message message = Message.builder()
                .senderTable(senderTable)
                .receiverTable(receiverTable)
                .sendHeartCount(dto.getSendHeartCount())
                .message(dto.getMessage())
                .build();
        repository.save(message);

        senderTable.sendHeart(dto.getSendHeartCount());
        receiverTable.receiveHeart(dto.getSendHeartCount());

        Locale locale = LocaleContextHolder.getLocale();
        List<User> users = receiverTable.getUsers();
        for (User user : users) {
            String body = messageSource.getMessage("sms.send-message", new Object[]{receiverTable.getTableNumber()}, locale);
            smsService.sendSMS(user.getPhone(), body);
        }

    }
}
