package com.example._test.dto.user.response;

import com.example._test.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetUserResponseDto {
    private Long id;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public GetUserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
