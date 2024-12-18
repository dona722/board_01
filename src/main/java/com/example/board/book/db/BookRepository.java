package com.example.board.book.db;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    // 도서명으로 검색
    List<BookEntity> findByBookNameContainingOrderByIdDesc(String keyword);
    
    // 출판사로 검색
    List<BookEntity> findByPublisherContainingOrderByIdDesc(String keyword);

    List<BookEntity> findByBookNameContainingIgnoreCase(String keyword);

    List<BookEntity> findTop5ByOrderByIdDesc(); 
}
