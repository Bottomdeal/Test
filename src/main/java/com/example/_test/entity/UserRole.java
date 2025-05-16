package com.example._test.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

// +) user_roles 테이블을 엔티티로 만들어야 하는 경우
//      : 중간 테이블에 추가 정보가 존재하는 경우
//      >> 역할 부여 날짜(assigned_at), 역할의 상태(is_active) 부여자 ID(assigned_by) 등등
//@Entity
//@Table(name = "user_roles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRole {
    @Embedded
    private UserRoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

//    private LocalDateTime assignedAt;

    @Builder
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
        this.id = new UserRoleId(user.getId(), role.getId());
    }
}