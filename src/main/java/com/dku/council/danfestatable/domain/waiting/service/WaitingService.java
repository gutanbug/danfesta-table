package com.dku.council.danfestatable.domain.waiting.service;

import com.dku.council.danfestatable.domain.matchtable.exception.MatchingTableNotFoundException;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.matchtable.repository.MatchingTableRepository;
import com.dku.council.danfestatable.domain.waiting.exception.WaitingNotFoundException;
import com.dku.council.danfestatable.domain.waiting.model.WaitingStatus;
import com.dku.council.danfestatable.domain.waiting.model.dto.response.ResponseWaitingDto;
import com.dku.council.danfestatable.domain.waiting.model.entity.Waiting;
import com.dku.council.danfestatable.domain.waiting.repository.WaitingRepository;
import com.dku.council.danfestatable.domain.waiting.repository.spec.WaitingSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WaitingService {

    private final WaitingRepository repository;
    private final MatchingTableRepository tableRepository;

    @Transactional(readOnly = true)
    public List<ResponseWaitingDto> list(WaitingStatus status) {
        Specification<Waiting> spec = WaitingSpec.withStatus(status);
        List<Waiting> result = repository.findAll(spec);
        return result.stream().map(ResponseWaitingDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void approveWaiting(Long id) {
        Waiting waiting = repository.findById(id).orElseThrow(WaitingNotFoundException::new);

        if (tableRepository.findByTableNumber(waiting.getTableNumber()).isEmpty()) {
            MatchingTable table = MatchingTable.builder()
                    .tableNumber(waiting.getTableNumber())
                    .build();
            tableRepository.save(table);
            table.addUser(waiting.getUser());
            waiting.getUser().setMatchingTable(table);
            waiting.changeToApproval();
        } else {
            MatchingTable table = tableRepository.findByTableNumber(waiting.getTableNumber()).orElseThrow(MatchingTableNotFoundException::new);
            if(waiting.getUser().getMatchingTable() != null) {
                waiting.getUser().getMatchingTable().removeUser(waiting.getUser());
            }
            table.addUser(waiting.getUser());
            waiting.getUser().setMatchingTable(table);
            waiting.changeToApproval();
        }
    }

    @Transactional
    public void rejectWaiting(Long id) {
        Waiting waiting = repository.findById(id).orElseThrow(WaitingNotFoundException::new);
        waiting.changeToReject();
    }
}
