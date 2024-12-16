package com.example.board.post.controller;

import com.example.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final PostService postService;
    
    @GetMapping("")
    public String noticeList(Model model) {
        var notices = postService.getNoticeList();
        model.addAttribute("posts", notices);
        return "notice/list";
    }
}