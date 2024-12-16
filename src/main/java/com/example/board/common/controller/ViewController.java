package com.example.board.common.controller;

import com.example.board.book.service.BookService;
import com.example.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {
    
    private final BookService bookService;
    private final BoardService boardService;
    
    @GetMapping("/")
    public String index(Model model) {
        // 신규 도서 목록 조회 (최근 4개)
        model.addAttribute("newBooks", bookService.getBookList().stream().limit(4).toList());
        
        // 공지사항 목록 조회 (최근 5개)
        model.addAttribute("notices", boardService.getNotices(5));
        
        // 문의글 목록 조회 (최근 5개)
        model.addAttribute("inquiries", boardService.getInquiries(5));
        
        return "index";
    }
}