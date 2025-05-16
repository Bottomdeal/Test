package com.example._test.service;

import com.example._test.dto.request.PostCreateRequestDto;
import com.example._test.dto.request.PostUpdateRequestDto;
import com.example._test.dto.response.PostListResponseDto;
import com.example._test.dto.response.ResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {
    @Transactional
    ResponseDto<PostListResponseDto> createPost(PostCreateRequestDto dto);

    @Transactional(readOnly = true)
    ResponseDto<PostListResponseDto> getPostById(Long id);

    @Transactional(readOnly = true)
    ResponseDto<List<PostListResponseDto>> getAllPosts();

    @Transactional
    ResponseDto<PostListResponseDto> updatePost(Long id, PostUpdateRequestDto dto);

    ResponseDto<Void> deletePost(Long id);
}
