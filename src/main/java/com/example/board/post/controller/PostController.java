package com.example.board.post.controller;

import com.example.board.common.api.ApiResponse;
import com.example.board.post.model.PostRequest;
import com.example.board.post.model.PostDto;
import com.example.board.post.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ApiResponse<PostDto> getPost(@PathVariable Long id) {
        return ApiResponse.success(postService.getPost(id));
    }

    @GetMapping
    public ApiResponse<List<PostDto>> getAllPosts() {
        return ApiResponse.success(postService.getAllPosts());
    }

    @PostMapping
    public ApiResponse<PostDto> createPost(@RequestBody @Valid PostRequest request) {
        log.info("받은 데이터: {}", request);  // 로그 추가
        try {
            PostDto postDto = postService.createPost(request);
            return ApiResponse.success(postDto);
        } catch (Exception e) {
            log.error("게시글 생성 중 오류 발생", e);
            return ApiResponse.error(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ApiResponse<PostDto> updatePost(
        @PathVariable Long id, 
        @RequestBody PostRequest request
    ) {
        return ApiResponse.success(postService.updatePost(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePost(
        @PathVariable Long id,
        @RequestParam String password
    ) {
        postService.deletePost(id, password);
        return ApiResponse.success();
    }
}
