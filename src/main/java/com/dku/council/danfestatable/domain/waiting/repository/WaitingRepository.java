package com.dku.council.danfestatable.domain.waiting.repository;

import com.dku.council.danfestatable.domain.waiting.model.entity.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WaitingRepository extends JpaRepository<Waiting, Long>, JpaSpecificationExecutor<Waiting> {
}