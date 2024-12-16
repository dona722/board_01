package com.example.board.orders.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInquiryRequest {
    private Long orderId;
    private Long bookId;
    private String title;
    private String content;
}