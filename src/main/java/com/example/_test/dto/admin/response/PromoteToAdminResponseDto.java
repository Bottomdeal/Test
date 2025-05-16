package com.example._test.dto.admin.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PromoteToAdminResponseDto {
    private String username;   // 권한이 변경된 사용자 이름
    private List<String> roles; // 가지고 있는 권한
    private String message;
}
