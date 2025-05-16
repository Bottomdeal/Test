package com.example._test.service;

import com.example._test.dto.request.NoticeCreateRequestDto;
import com.example._test.dto.response.NoticeResponseDto;
import com.example._test.dto.response.ResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface NoticeService {
    @Transactional
    ResponseDto<NoticeResponseDto> createNotice(NoticeCreateRequestDto dto);
}
