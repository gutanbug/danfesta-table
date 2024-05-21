package com.dku.council.danfestatable.domain.waiting.repository;

import com.dku.council.danfestatable.domain.waiting.model.entity.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WaitingRepository extends JpaRepository<Waiting, Long>, JpaSpecificationExecutor<Waiting> {

    @Query("select w from Waiting w where w.id = :userId and w.waitingStatus = 'WAITING'")
    Optional<Waiting> findByUserIdWithWaiting(@Param("userId") Long userId);
}