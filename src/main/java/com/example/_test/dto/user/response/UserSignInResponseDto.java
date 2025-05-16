package com.example._test.dto.user.response;

import com.example._test.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignInResponseDto {
    private String token; // jwt 토큰
    private User user;
    private int exprTime; // expire + time: (토큰) 만료 시간
}
