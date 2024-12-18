package com.example.board.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.book.service.BookService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookViewController {
    
    private final BookService bookService;

    // 도서 목록 페이지
    @GetMapping("")
    public String listPage(
        @RequestParam(required = false) String keyword,
        Model model
    ) {
        model.addAttribute("books", bookService.searchBooks(keyword));
        model.addAttribute("keyword", keyword); // 검색어 유지를 위해 추가
        return "book/list";
    }

    @GetMapping("/{bookId}")
    public String detailPage(@PathVariable Long bookId, Model model) {
        model.addAttribute("book", bookService.getBookDetail(bookId));
        return "book/detail";
    }
}