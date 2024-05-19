package com.dku.council.danfestatable.domain.heart_payment.repository;

import com.dku.council.danfestatable.domain.heart_payment.model.entity.HeartPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HeartPaymentRepository extends JpaRepository<HeartPayment, Long>, JpaSpecificationExecutor<HeartPayment> {
    @Query("select hp from HeartPayment hp where hp.id = :heartPaymentId and hp.paymentStatus = 'WAITING'")
    Optional<HeartPayment> findByHeartPaymentId(@Param("heartPaymentId") Long heartPaymentId);
}
