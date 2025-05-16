package com.example._test.controller;

import com.example._test.common.ApiMappingPattern;
import com.example._test.dto.request.NoticeCreateRequestDto;
import com.example._test.dto.response.NoticeResponseDto;
import com.example._test.dto.response.ResponseDto;
import com.example._test.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiMappingPattern.NOTICE_API)
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService = null;

    // 1) 게시글 생성
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    // @Valid
    // : DTO 객체에 대한 검증을 수행하는 애너테이션
    // : 사용자가 클라이언트로부터 전달한 데이터가 미리 정의된 규칙에 맞는지 확인
    // - DTO 내에서 정의된 규칙에 맞지 않으면 에러 발생
    public ResponseEntity<ResponseDto<NoticeResponseDto>> createNotice(@Valid @RequestBody NoticeCreateRequestDto dto) {
        ResponseDto<NoticeResponseDto> notice = noticeService.createNotice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(notice);
    }
}
