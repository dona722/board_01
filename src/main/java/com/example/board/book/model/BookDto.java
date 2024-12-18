package com.example.board.book.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String bookName;
    private String publisher;
    private Integer price;
    private Integer stock;
    private String status;
}