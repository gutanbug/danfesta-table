package com.dku.council.danfestatable.domain.matchtable.service;

import com.dku.council.danfestatable.domain.matchtable.exception.MatchingTableNotFoundException;
import com.dku.council.danfestatable.domain.matchtable.model.dto.response.ResponseMyTableDto;
import com.dku.council.danfestatable.domain.matchtable.model.dto.response.SummarizedTableDto;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.matchtable.repository.MatchingTableRepository;
import com.dku.council.danfestatable.domain.user.exception.UserNotFoundException;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import com.dku.council.danfestatable.domain.user.repository.UserRepository;
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

    public List<SummarizedTableDto> list() {
        List<MatchingTable> lists = repository.findAllWithActive();
        return lists.stream().map(SummarizedTableDto::new).collect(Collectors.toList());
    }

    public ResponseMyTableDto getMyTable(Long userId) {
        MatchingTable table = repository.findByUserId(userId).orElseThrow(MatchingTableNotFoundException::new);
        return new ResponseMyTableDto(table);
    }

    @Transactional
    public void joinTable(Long userId, int number) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (repository.findByTableNumber(number).isEmpty()) {
            MatchingTable table = MatchingTable.builder()
                    .tableNumber(number)
                    .build();
            repository.save(table);
            table.addUser(user);
            user.setMatchingTable(table);
        } else {
            MatchingTable table = repository.findByTableNumber(number).orElseThrow(MatchingTableNotFoundException::new);
            user.getMatchingTable().removeUser(user);
            table.addUser(user);
            user.setMatchingTable(table);
        }
    }

    // TODO : 하트 결제 로직
}
