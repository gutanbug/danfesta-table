package com.dku.council.danfestatable.domain.message.repository;

import com.dku.council.danfestatable.domain.message.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m where m.receiverTable.id = :receiverTableId order by m.createdAt desc")
    List<Message> findByReceiverTableId(@Param("receiverTableId") Long receiverTableId);
}
