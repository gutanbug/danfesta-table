package com.dku.council.danfestatable.domain.matchtable.repository;

import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MatchingTableRepository extends JpaRepository<MatchingTable, Long> {
    @Query("select mt from MatchingTable mt where mt.tableStatus = 'ACTIVE'")
    List<MatchingTable> findAllWithActive();

    @Query("select mt from MatchingTable mt where mt.tableNumber = :tableNumber and mt.tableStatus = 'ACTIVE'")
    Optional<MatchingTable> findByTableNumber(@Param("tableNumber") int tableNumber);

    @Query("select mt from MatchingTable mt join fetch mt.users u " +
            "where u.id = :userId and mt.tableStatus = 'ACTIVE'")
    Optional<MatchingTable> findByUserId(@Param("userId") Long userId);
}
