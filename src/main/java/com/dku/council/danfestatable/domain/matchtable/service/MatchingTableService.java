package com.dku.council.danfestatable.domain.matchtable.service;

import com.dku.council.danfestatable.domain.matchtable.exception.AlreadyWaitingException;
import com.dku.council.danfestatable.domain.matchtable.exception.MatchingTableNotFoundException;
import com.dku.council.danfestatable.domain.matchtable.model.dto.response.ResponseMyTableDto;
import com.dku.council.danfestatable.domain.matchtable.model.dto.response.SummarizedTableDto;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.matchtable.repository.MatchingTableRepository;
import com.dku.council.danfestatable.domain.message.repository.MessageRepository;
import com.dku.council.danfestatable.domain.user.exception.UserNotFoundException;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import com.dku.council.danfestatable.domain.user.repository.UserRepository;
import com.dku.council.danfestatable.domain.waiting.model.entity.Waiting;
import com.dku.council.danfestatable.domain.waiting.repository.WaitingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MatchingTableService {

    private final MatchingTableRepository repository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final WaitingRepository waitingRepository;

    public List<SummarizedTableDto> list() {
        List<MatchingTable> lists = repository.findAllWithActive();
        return lists.stream().map(SummarizedTableDto::new).collect(Collectors.toList());
    }

    public ResponseMyTableDto getMyTable(Long userId) {
        MatchingTable table = repository.findByUserId(userId).orElseThrow(MatchingTableNotFoundException::new);
        int receiveMessageCount = messageRepository.countReceiveSendMessage(table.getId());
        return new ResponseMyTableDto(table, receiveMessageCount);
    }

    @Transactional
    public void joinTable(Long userId, int number) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if(waitingRepository.findByUserIdWithWaiting(user.getId()).isPresent()) {
            throw new AlreadyWaitingException();
        }

        Waiting waiting = Waiting.builder()
                .user(user)
                .tableNumber(number)
                .build();
        waitingRepository.save(waiting);
    }

    // TODO : 하트 결제 로직
}
