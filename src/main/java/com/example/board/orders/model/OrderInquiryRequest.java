package com.example.board.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInquiryRequest {
    private Long orderId;
    private Long bookId;  // optional
    private String title;
    private String content;
}