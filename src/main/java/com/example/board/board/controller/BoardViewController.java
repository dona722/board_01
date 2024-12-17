// src/main/java/com/example/board/board/controller/BoardViewController.java
package com.example.board.board.controller;

import com.example.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardViewController {
    
    private final PostService postService;
    
    @GetMapping("/boards")
public String boardList(Model model) {
    model.addAttribute("notices", postService.getNotices(5));
    model.addAttribute("inquiries", postService.getInquiries(5));
    return "board/list";  // boards/list -> board/list로 수정
}
}