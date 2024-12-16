package com.example.board.board.controller;

import com.example.board.board.db.BoardEntity;
import com.example.board.board.db.BoardRepository;
import com.example.board.board.model.BoardDto;
import com.example.board.board.model.BoardRequest;
import com.example.board.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @PostMapping("")
    public BoardDto create(
            @Valid
            @RequestBody BoardRequest boardRequest
    ){
        return boardService.create(boardRequest);
    }


    @GetMapping("/id/{id}")
    public BoardDto view(
            @PathVariable Long id
    ){
        var entity = boardService.view(id);
        log.info("result : {}",entity);
        return entity;
    }

    @GetMapping("/ids/{id}")
    public List<BoardEntity> all(
            @PathVariable Long id
    ){
        return boardRepository.findAll();
    }
}
