package com.example._test.service.implementations;

import com.example._test.common.ResponseMessage;
import com.example._test.dto.request.NoticeCreateRequestDto;
import com.example._test.dto.response.NoticeResponseDto;
import com.example._test.dto.response.ResponseDto;
import com.example._test.entity.Notice;
import com.example._test.repository.NoticeRepository;
import com.example._test.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    @Override
    public ResponseDto<NoticeResponseDto> createNotice(NoticeCreateRequestDto dto) {
        NoticeResponseDto responseDto = null;

        Notice newNotice = Notice.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();

        Notice saved = noticeRepository.save(newNotice);

        responseDto = NoticeResponseDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .content(saved.getContent())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }
}