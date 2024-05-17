package com.dku.council.danfestatable.domain.user.model.entity;

import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.user.model.Enrolled;
import com.dku.council.danfestatable.domain.user.model.UserRole;
import com.dku.council.danfestatable.global.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @Column(name = "danfesta_user_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private String password;

    @NotNull
    private String gender;

    @NotBlank
    private String loginId;

    @Enumerated(STRING)
    private Enrolled enrolled;

    @Enumerated(STRING)
    private UserRole userRole;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_table_id")
    private MatchingTable matchingTable;

    @Builder
    private User(String name, String phone, String gender, String loginId, String password, Enrolled enrolled) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.loginId = loginId;
        this.password = password;
        this.enrolled = enrolled;
        this.userRole = UserRole.USER;
    }
}
