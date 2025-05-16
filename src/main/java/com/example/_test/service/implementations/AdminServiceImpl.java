package com.example._test.service.implementations;

import com.example._test.common.ResponseMessage;
import com.example._test.dto.admin.request.PutAuthorityRequestDto;
import com.example._test.dto.admin.response.DemoteFromAdminResponseDto;
import com.example._test.dto.admin.response.PromoteToAdminResponseDto;
import com.example._test.dto.response.ResponseDto;
import com.example._test.entity.Role;
import com.example._test.entity.User;
import com.example._test.repository.RoleRepository;
import com.example._test.repository.UserRepository;
import com.example._test.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public ResponseDto<PromoteToAdminResponseDto> promoteUserToAdmin(PutAuthorityRequestDto dto) {
        PromoteToAdminResponseDto data = null;

        // 1. 사용자 조회
        User user = (User) userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_USER));

        // 2. 권한 생성 또는 조회
        Role adminRole = roleRepository.findByRoleName("ADMIN")
                .orElseGet(() -> roleRepository.save(Role.builder().roleName("ADMIN").build()));

        // .anyMatch()
        // : 각 요소를 순회하면서 조건에 부합하는 요소가 단 하나라도 존재하면 true 반환, 그렇지 않으면 false 반환
        boolean alreadyHasAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals("ADMIN"));

        if (alreadyHasAdmin) {
            throw new IllegalStateException("이미 ADMIN 권한을 가지고 있습니다.");
        }

        // 권한 추가
        user.getRoles().add(adminRole);

        // 3. 사용자 권한 수정 + 저장
        User savedUser = userRepository.save(user);

        List<String> roleList = savedUser.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());


        data = new PromoteToAdminResponseDto(user.getUsername(), roleList, "권한 변경이 정상적으로 이루어졌습니다.");

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<DemoteFromAdminResponseDto> demoteUserFromAdmin(PutAuthorityRequestDto dto) {

        DemoteFromAdminResponseDto data = null;

        // 1. 사용자 조회
        User user = (User) userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_USER));

        // 2. 현재 권한 목록에서 ADMIN 제거 후 updatedRoles에 담기
        List<Role> updatedRoles = user.getRoles().stream()
                .filter(role -> role.getRoleName().equals("ADMIN"))
                .collect(Collectors.toList());

        // 3. User 권한 여부 확인
        boolean hasUser = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals("USER"));

        // User 권한이 만약 없다면
        if (!hasUser) {
            Role userRole = roleRepository.findByRoleName("USER")
                    .orElseGet(() -> roleRepository.save(Role.builder().roleName("USER").build()));
            user.getRoles().add(userRole);
        }

        User savedUser = userRepository.save(user);

        List<String> roleList = savedUser.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());

        data = new DemoteFromAdminResponseDto(user.getUsername(), roleList, "권한 변경이 정상적으로 이루어졌습니다.");

        // 4. 로그 저장
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}