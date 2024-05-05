package com.dku.council.danfestatable.domain.admin.service;

import com.dku.council.danfestatable.domain.admin.dto.list.SummarizedTableAdminDto;
import com.dku.council.danfestatable.domain.admin.dto.request.RequestCreateTableDto;
import com.dku.council.danfestatable.domain.matchtable.exception.AlreadyMatchingTableException;
import com.dku.council.danfestatable.domain.matchtable.exception.MatchingTableNotFoundException;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.matchtable.repository.MatchingTableRepository;
import com.dku.council.danfestatable.domain.user.model.UserRole;
import com.dku.council.danfestatable.global.error.exception.NotGrantedException;
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
public class MatchingTableAdminService {

    private final MatchingTableRepository repository;

    public List<SummarizedTableAdminDto> list() {
        return repository.findAllWithActive()
                .stream().map(SummarizedTableAdminDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(RequestCreateTableDto dto) {
        if (repository.findByTableNumber(Integer.parseInt(dto.getTableNumber())).isPresent()) {
            throw new AlreadyMatchingTableException();
        }
        MatchingTable table = MatchingTable.builder()
                .tableNumber(Integer.parseInt(dto.getTableNumber()))
                .build();
        repository.save(table);
    }

    @Transactional
    public void addTime(Long id) {
        MatchingTable table = repository.findById(id).orElseThrow(MatchingTableNotFoundException::new);
        table.addTime();
    }

    @Transactional
    public void delete(Long id) {
        MatchingTable table = repository.findById(id).orElseThrow(MatchingTableNotFoundException::new);
        table.markedAsInactive();
    }
}
