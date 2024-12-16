package com.example.board.board.controller;

import com.example.board.board.db.BoardEntity;
import com.example.board.board.db.BoardRepository;
import com.example.board.board.model.BoardDto;
import com.example.board.board.model.BoardRequest;
import com.example.board.board.service.BoardService;
import com.example.board.common.api.ApiResponse;
import com.example.board.post.model.PostDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @GetMapping("/{id}")
    public ApiResponse<BoardDto> getBoard(@PathVariable Long id) {
        return ApiResponse.success(boardService.get(id));
    }

    @GetMapping
    public ApiResponse<List<BoardDto>> getAllBoards() {
        return ApiResponse.success(boardService.getAll());
    }

    @PostMapping
    public ApiResponse<BoardDto> createBoard(@RequestBody BoardRequest request) {
        return ApiResponse.success(boardService.create(request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return ApiResponse.success();
    }

    @GetMapping("/{boardId}/posts")
    public ApiResponse<List<PostDto>> getBoardPosts(@PathVariable Long boardId) {
        return ApiResponse.success(boardService.getBoardPosts(boardId));
    }
}
