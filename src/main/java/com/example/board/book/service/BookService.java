package com.example.board.book.service;

import com.example.board.book.db.BookEntity;
import com.example.board.book.db.BookRepository;
import com.example.board.book.model.BookDto;
import com.example.board.book.model.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    
    @Value("${book.admin.password}")
    private String adminPassword;

    // 도서 목록 조회
    public List<BookDto> getBookList() {
        return bookRepository.findAll().stream()
            .map(this::convertToDto)
            .toList();
    }

    // 도서 상세 조회
    public BookDto getBookDetail(Long bookId) {
        return bookRepository.findById(bookId)
            .map(this::convertToDto)
            .orElseThrow(() -> new RuntimeException("도서를 찾을 수 없습니다."));
    }

    // 도서 추가
    @Transactional
    public BookDto createBook(BookRequest request) {
        validateAdminPassword(request.getPassword());
        
        var entity = BookEntity.builder()
            .bookName(request.getBookName())
            .publisher(request.getPublisher())
            .price(request.getPrice())
            .stock(request.getStock())
            .status("AVAILABLE")
            .build();
            
        return convertToDto(bookRepository.save(entity));
    }

    // 도서 수정
    @Transactional
    public BookDto updateBook(Long bookId, BookRequest request) {
        validateAdminPassword(request.getPassword());
        
        return bookRepository.findById(bookId)
            .map(entity -> {
                entity.setBookName(request.getBookName());
                entity.setPublisher(request.getPublisher());
                entity.setPrice(request.getPrice());
                entity.setStock(request.getStock());
                return convertToDto(bookRepository.save(entity));
            })
            .orElseThrow(() -> new RuntimeException("도서를 찾을 수 없습니다."));
    }

    // 도서 삭제
    @Transactional
    public void deleteBook(Long bookId, String password) {
        validateAdminPassword(password);
        bookRepository.deleteById(bookId);
    }

    // 비밀번호 검증
    private void validateAdminPassword(String password) {
        if (!adminPassword.equals(password)) {
            throw new RuntimeException("관리자 비밀번호가 일치하지 않습니다.");
        }
    }

    // Entity -> DTO 변환
    private BookDto convertToDto(BookEntity entity) {
        return BookDto.builder()
            .id(entity.getId())
            .bookName(entity.getBookName())
            .publisher(entity.getPublisher())
            .price(entity.getPrice())
            .stock(entity.getStock())
            .status(entity.getStatus())
            .build();
    }

    public List<BookDto> searchBooks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        }
        
        return bookRepository.findByBookNameContainingIgnoreCase(keyword).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public List<BookDto> getLatestBooks() {
        return bookRepository.findTop5ByOrderByIdDesc().stream()
            .map(this::convertToDto)
            .toList();
    }


}