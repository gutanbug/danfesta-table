package com.dku.council.danfestatable.domain.heart_payment.service;

import com.dku.council.danfestatable.domain.heart_payment.model.dto.request.RequestCreateHeartPaymentDto;
import com.dku.council.danfestatable.domain.heart_payment.model.entity.HeartPayment;
import com.dku.council.danfestatable.domain.heart_payment.repository.HeartPaymentRepository;
import com.dku.council.danfestatable.domain.matchtable.exception.MatchingTableNotFoundException;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.matchtable.repository.MatchingTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeartPaymentService {

    private final HeartPaymentRepository repository;
    private final MatchingTableRepository tableRepository;

    public void createHeartPayment(Long userId, RequestCreateHeartPaymentDto dto) {
        MatchingTable table = tableRepository.findByUserId(userId)
                .orElseThrow(MatchingTableNotFoundException::new);
        HeartPayment heartPayment = HeartPayment.builder()
                .table(table)
                .requiredHeartCount(dto.getRequiredHeartCount())
                .totalPrice(dto.getRequiredHeartCount() * 500)
                .build();
        repository.save(heartPayment);
    }
}
