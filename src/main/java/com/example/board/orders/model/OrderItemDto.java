package com.example.board.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    
    // 도서 정보
    private Long bookId;
    private String bookName;
    
    // 주문 상세
    private Integer quantity;  // 수량
    private Integer price;     // 단가
    private Integer amount;    // 합계 (quantity * price)
}