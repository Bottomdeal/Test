package com.example._test.service.implementations;

import com.example._test.common.ResponseMessage;
import com.example._test.dto.admin.request.PutAuthorityRequestDto;
import com.example._test.dto.request.PostCreateRequestDto;
import com.example._test.dto.request.PostUpdateRequestDto;
import com.example._test.dto.response.PostListResponseDto;
import com.example._test.dto.response.ResponseDto;
import com.example._test.entity.Post;
import com.example._test.entity.User;
import com.example._test.repository.PostRepository;
import com.example._test.repository.UserRepository;
import com.example._test.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public ResponseDto<PostListResponseDto> createPost(PostCreateRequestDto dto) {
        PostListResponseDto responseDto = null;

        User user = (User) userRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_USER));

        Post newPost = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();

        Post saved = postRepository.save(newPost);

        responseDto = PostListResponseDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .content(saved.getContent())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseDto<PostListResponseDto> getPostById(Long id) {
        PostListResponseDto responseDto = null;

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        responseDto = PostListResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseDto<List<PostListResponseDto>> getAllPosts() {
        List<PostListResponseDto> responseDtos = null;

        List<Post> posts = postRepository.findAll();

        responseDtos = posts.stream()
                .map(post -> PostListResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
    }

    @Transactional
    @Override
    public ResponseDto<PostListResponseDto> updatePost(Long id, PostUpdateRequestDto dto) {
        PostListResponseDto responseDto = null;

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        Post updatedPost = postRepository.save(post);

        responseDto = PostListResponseDto.builder()
                .id(updatedPost.getId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<Void> deletePost(Long id) {
//        if (!postRepository.existsById(id)) {
//            // .existsById(PK값)
//            // : 존재하면 true, 존재하지 않으면 false 반환
//            throw new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id);
//        }

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));


        postRepository.deleteById(id);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}