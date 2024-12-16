package com.example.board.post.controller;

import com.example.board.common.api.ApiResponse;
import com.example.board.post.model.PostRequest;
import com.example.board.post.model.PostDto;
import com.example.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ApiResponse<PostDto> createPost(@RequestBody PostRequest request) {
        return ApiResponse.success(postService.createPost(request));
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
