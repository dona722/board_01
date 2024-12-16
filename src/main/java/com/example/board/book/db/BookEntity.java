package com.example.board.book.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private String publisher;

    private Integer price;
    
    private Integer stock;  // 재고 수량
    
    private String status;  // 상태 (AVAILABLE, SOLD_OUT 등)
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}