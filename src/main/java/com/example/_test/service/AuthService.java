package com.example._test.service;

import com.example._test.dto.response.ResponseDto;
import com.example._test.dto.user.request.UserSignInRequestDto;
import com.example._test.dto.user.request.UserSignUpRequestDto;
import com.example._test.dto.user.response.UserSignInResponseDto;
import com.example._test.dto.user.response.UserSignUpResponseDto;

public interface AuthService {
    // 1) 회원 가입
    ResponseDto<UserSignUpResponseDto> signup(UserSignUpRequestDto dto);

    // 2) 로그인
    ResponseDto<UserSignInResponseDto> login(UserSignInRequestDto dto);
}
