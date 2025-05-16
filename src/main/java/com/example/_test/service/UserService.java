package com.example._test.service;

import com.example._test.dto.response.ResponseDto;
import com.example._test.dto.user.response.GetUserResponseDto;

public interface UserService {
    ResponseDto<GetUserResponseDto> getUserInfo(String username);
}
