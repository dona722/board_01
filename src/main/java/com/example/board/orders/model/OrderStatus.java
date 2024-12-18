package com.example.board.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    ORDERED("주문완료"),
    PAID("결제완료"),
    PREPARING("상품준비중"),
    SHIPPING("배송중"),
    DELIVERED("배송완료"),
    CANCELED("주문취소");

    private final String description;

    public static OrderStatus fromString(String status) {
        try {
            return OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid order status: " + status);
        }
    }

    public boolean isChangeable() {
        // 배송완료, 주문취소 상태에서는 상태 변경 불가
        return this != DELIVERED && this != CANCELED;
    }

    public boolean isCancelable() {
        // 배송중, 배송완료 상태에서는 취소 불가
        return this != SHIPPING && this != DELIVERED;
    }
}