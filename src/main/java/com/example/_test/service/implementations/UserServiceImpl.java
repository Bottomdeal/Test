package com.example._test.service.implementations;

import com.example._test.common.ResponseMessage;
import com.example._test.dto.response.ResponseDto;
import com.example._test.dto.user.response.GetUserResponseDto;
import com.example._test.entity.User;
import com.example._test.repository.UserRepository;
import com.example._test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public ResponseDto<GetUserResponseDto> getUserInfo(String username) {

        User user = (User) userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }

        GetUserResponseDto data = new GetUserResponseDto(user);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

}