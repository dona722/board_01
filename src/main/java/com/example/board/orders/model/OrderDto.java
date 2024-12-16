package com.example.board.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String customerName;
    private String customerPhone;
    private String address;
    private String status;
    private LocalDateTime orderedAt;
    private List<OrderItemDto> items;
    private Integer totalAmount;
}