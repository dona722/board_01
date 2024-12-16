package com.example.board.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    @NotNull(message = "도서 ID는 필수입니다")
    private Long bookId;
    
    @NotNull(message = "수량은 필수입니다")
    private Integer quantity;
}