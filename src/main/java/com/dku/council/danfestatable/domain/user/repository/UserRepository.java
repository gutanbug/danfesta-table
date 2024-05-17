package com.dku.council.danfestatable.domain.user.repository;

import com.dku.council.danfestatable.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.loginId = :loginId")
    Optional<User> findByLoginId(@Param("loginId") String loginId);

    @Query("select u from MatchingTable mt join mt.users u " +
            "where mt.id = :id and mt.tableStatus = 'ACTIVE'")
    List<User> findAllUsersByTableId(@Param("id") Long id);

    @Query("select u from User u where u.loginId = :email")
    Optional<User> findUserByLoginId(@Param("email") String email);

    @Query("select u from User u where u.phone = :phone")
    Optional<User> findByPhone(@Param("phone") String phone);
}
