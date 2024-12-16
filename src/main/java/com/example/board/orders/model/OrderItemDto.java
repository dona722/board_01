package com.example.board.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    private Long bookId;
    private String bookName;
    private Integer quantity;
    private Integer price;
    private Integer amount;  // price * quantity
}