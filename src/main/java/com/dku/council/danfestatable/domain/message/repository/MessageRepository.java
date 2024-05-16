package com.dku.council.danfestatable.domain.message.repository;

import com.dku.council.danfestatable.domain.message.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
