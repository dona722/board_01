package com.example.board.common.controller;

import com.example.board.book.service.BookService;
import com.example.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {
    
    private final BookService bookService;
    private final PostService postService;
    
    @GetMapping("/")
    public String index(Model model) {
        // 신규 도서 목록 조회 (최근 4개)
    // 신규 도서 목록 조회 (최근 4개)
    model.addAttribute("latestBooks", bookService.getLatestBooks());
    
    // 공지사항과 문의글 목록 조회 (최근 5개씩)
    model.addAttribute("notices", postService.getNotices(5));
    model.addAttribute("inquiries", postService.getInquiries(5));
        
        return "index";
    }
    @GetMapping("/inquiry/write")
    public String inquiryWrite() {
        return "post/inquiry-write";  // post/write -> post/inquiry-write로 변경
    }

    @GetMapping("/notice")
public String noticeList(Model model) {
    model.addAttribute("posts", postService.getNotices(10));  // 10개의 공지사항 조회
    return "notice/list";
}

    @GetMapping("/inquiry/{id}")
    public String inquiryDetail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "post/detail";  // detail.html 템플릿 사용
    }

    @GetMapping("/order-inquiry/write")
    public String orderInquiryWrite(Model model) {
        log.info("주문 문의글 작성 페이지 요청");  // 로그 추가
        return "post/order-inquiry-write";
    }
}