package com.dku.council.danfestatable.domain.user.model.entity;

import com.dku.council.danfestatable.domain.user.model.UserRole;
import com.dku.council.danfestatable.global.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "danfesta_user",
        indexes = {
            @Index(name = "idx_user_name", columnList = "name")
        })
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String phone;

    private String gender;

    private String loginId;

    @Enumerated(STRING)
    private UserRole userRole;

    @Builder
    private User(String phone, String gender, String loginId) {
        this.phone = phone;
        this.gender = gender;
        this.loginId = loginId;
        this.userRole = UserRole.USER;
    }
}
