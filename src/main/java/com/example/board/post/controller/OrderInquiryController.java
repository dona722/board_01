package com.example.board.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.board.post.service.PostService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/order-inquiry")
@RequiredArgsConstructor
public class OrderInquiryController {
    private final PostService postService;
    
    @GetMapping("")
    public String orderInquiryList(Model model) {
        var orderInquiries = postService.getOrderInquiryList();
        model.addAttribute("posts", orderInquiries);
        return "order-inquiry/list";
    }
}