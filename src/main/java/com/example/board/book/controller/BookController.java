package com.example.board.book.controller;

import com.example.board.book.model.BookDto;
import com.example.board.book.model.BookRequest;
import com.example.board.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 도서 목록 조회
    @GetMapping
    public List<BookDto> getBookList() {
        return bookService.getBookList();
    }

    // 도서 상세 조회
    @GetMapping("/{bookId}")
    public BookDto getBookDetail(@PathVariable Long bookId) {
        return bookService.getBookDetail(bookId);
    }

    // 도서 추가
    @PostMapping
    public BookDto createBook(@Valid @RequestBody BookRequest request) {
        return bookService.createBook(request);
    }

    // 도서 수정
    @PutMapping("/{bookId}")
    public BookDto updateBook(
        @PathVariable Long bookId, 
        @Valid @RequestBody BookRequest request
    ) {
        return bookService.updateBook(bookId, request);
    }

    // 도서 삭제
    @DeleteMapping("/{bookId}")
    public void deleteBook(
        @PathVariable Long bookId, 
        @RequestParam String password
    ) {
        bookService.deleteBook(bookId, password);
    }
}