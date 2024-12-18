package com.example.board.post.controller;

import com.example.board.post.model.PostDto;
import com.example.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {
    private final PostService postService;

    @GetMapping("")
    public String inquiryList(Model model) {
        var inquiries = postService.getInquiryList();
        model.addAttribute("posts", inquiries);
        return "inquiry/list";
    }
}