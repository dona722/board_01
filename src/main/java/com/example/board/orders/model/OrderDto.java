package com.example.board.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    
    // 고객 정보
    private String customerName;
    private String customerPhone;
    private String address;
    
    // 주문 정보
    private String status;
    private Integer totalAmount;
    private LocalDateTime orderedAt;
    
    // 주문 항목
    private List<OrderItemDto> items;

    // 추가 정보
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}