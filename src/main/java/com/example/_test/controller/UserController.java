package com.example._test.controller;

import com.example._test.common.ApiMappingPattern;
import com.example._test.dto.response.ResponseDto;
import com.example._test.dto.user.response.GetUserResponseDto;
import com.example._test.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.USER_API)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // === UserController mapping pattern === //
    private static final String GET_USER_INFO = "/me";

    // 1) 회원 정보 조회
    @GetMapping(GET_USER_INFO)
    public ResponseEntity<ResponseDto<GetUserResponseDto>> getUserInfo(
            // SecurityContextHolder에 저장된 인증 객체의 principal을 가져와서 사용
            // : 현재 인증된(로그인된) 사용자의 정보를 가져오는 애너테이션
            @AuthenticationPrincipal String userEmail
    ) {
        ResponseDto<GetUserResponseDto> response = userService.getUserInfo(userEmail);
        return ResponseEntity.ok(response);

    }







}
