package com.example._test.controller;

import com.example._test.common.ApiMappingPattern;
import com.example._test.dto.request.PostCreateRequestDto;
import com.example._test.dto.request.PostUpdateRequestDto;
import com.example._test.dto.response.PostListResponseDto;
import com.example._test.dto.response.ResponseDto;
import com.example._test.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @ResponseBody + @Controller
@RequestMapping(ApiMappingPattern.POST_API)
@RequiredArgsConstructor // 생성자 주입
public class PostController {

    // @Autowired // 의존성을 자동 주입 - 필드 주입
    private final PostService postService;

    // 1) 게시글 생성
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    // @Valid
    // : DTO 객체에 대한 검증을 수행하는 애너테이션
    // : 사용자가 클라이언트로부터 전달한 데이터가 미리 정의된 규칙에 맞는지 확인
    // - DTO 내에서 정의된 규칙에 맞지 않으면 에러 발생
    public ResponseEntity<ResponseDto<PostListResponseDto>> createPost(@Valid @RequestBody PostCreateRequestDto dto) {
        ResponseDto<PostListResponseDto> post = postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    // 2) 단건 조회
    @GetMapping("/{id}") //  "/api/v1/posts/{id}"
    public ResponseEntity<ResponseDto<PostListResponseDto>> getPostById(@PathVariable Long id) {
        ResponseDto<PostListResponseDto> post = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    // 3) 전체 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<PostListResponseDto>>> getAllPosts() {
        ResponseDto<List<PostListResponseDto>> posts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    // 4) 게시물 수정
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PostListResponseDto>> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostUpdateRequestDto dto
    ) {
        ResponseDto<PostListResponseDto> response = postService.updatePost(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 5) 게시물 삭제
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deletePost(@PathVariable Long id) {
        ResponseDto<Void> response = postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // =========================================================== //
    // PostController의 메인 경로: "/api/v1/posts"

}
