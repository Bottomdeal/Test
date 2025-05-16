package com.example._test.controller;

import com.example._test.common.ApiMappingPattern;
import com.example._test.dto.admin.request.PutAuthorityRequestDto;
import com.example._test.dto.admin.response.DemoteFromAdminResponseDto;
import com.example._test.dto.admin.response.PromoteToAdminResponseDto;
import com.example._test.dto.response.ResponseDto;
import com.example._test.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example._test.common.ApiMappingPattern.PUT_AUTHORITY_DEMOTE;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.ADMIN_API)
public class AdminController {
    private final AdminService adminService;

    // == AdminController mapping pattern == //
    private static final String PUT_AUTHORITY_TO_ADMIN = "/promote";

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(PUT_AUTHORITY_TO_ADMIN)
    public ResponseEntity<ResponseDto<PromoteToAdminResponseDto>> promoteUserToAdmin(
            @RequestBody PutAuthorityRequestDto dto
    ) {
        ResponseDto<PromoteToAdminResponseDto> response = adminService.promoteUserToAdmin(dto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(PUT_AUTHORITY_DEMOTE)
    public ResponseEntity<ResponseDto<DemoteFromAdminResponseDto>> demoteUserFromAdmin(
            @RequestBody PutAuthorityRequestDto dto
    ) {
        ResponseDto<DemoteFromAdminResponseDto> response = adminService.demoteUserFromAdmin(dto);
        return ResponseEntity.ok(response);
    }

}