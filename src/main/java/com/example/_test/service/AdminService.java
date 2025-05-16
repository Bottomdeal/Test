package com.example._test.service;

import com.example._test.dto.admin.request.PutAuthorityRequestDto;
import com.example._test.dto.admin.response.DemoteFromAdminResponseDto;
import com.example._test.dto.admin.response.PromoteToAdminResponseDto;
import com.example._test.dto.response.ResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface AdminService {
    @Transactional
    // 트랜잭션 처리를 선언적으로 해주는 Spring 기능
    // : 하나의 메서드 내의 모든 DB 작업을 하나의 트랜잭션으로 묶어줌
    ResponseDto<PromoteToAdminResponseDto> promoteUserToAdmin(PutAuthorityRequestDto dto);

    ResponseDto<DemoteFromAdminResponseDto> demoteUserFromAdmin(PutAuthorityRequestDto dto);
}
