package com.dku.council.danfestatable.domain.admin.service;

import com.dku.council.danfestatable.domain.heart_payment.exception.HeartPaymentNotFoundException;
import com.dku.council.danfestatable.domain.heart_payment.model.PaymentStatus;
import com.dku.council.danfestatable.domain.heart_payment.model.dto.list.SummarizedHeartPaymentDto;
import com.dku.council.danfestatable.domain.heart_payment.model.entity.HeartPayment;
import com.dku.council.danfestatable.domain.heart_payment.repository.HeartPaymentRepository;
import com.dku.council.danfestatable.domain.heart_payment.repository.spec.HeartPaymentSpec;
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
public class HeartPaymentAdminService {

    private final HeartPaymentRepository repository;

    @Transactional(readOnly = true)
    public List<SummarizedHeartPaymentDto> listWithStatus(PaymentStatus status) {
        Specification<HeartPayment> spec = HeartPaymentSpec.withStatus(status);
        return repository.findAll(spec).stream().map(SummarizedHeartPaymentDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void approveHeartPayment(Long heartPaymentId) {
        HeartPayment heartPayment = repository.findByHeartPaymentId(heartPaymentId)
                .orElseThrow(HeartPaymentNotFoundException::new);
        heartPayment.approve();
    }

    @Transactional
    public void rejectHeartPayment(Long heartPaymentId) {
        HeartPayment heartPayment = repository.findByHeartPaymentId(heartPaymentId)
                .orElseThrow(HeartPaymentNotFoundException::new);
        heartPayment.reject();
    }
}
