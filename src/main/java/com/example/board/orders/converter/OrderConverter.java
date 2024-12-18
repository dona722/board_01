package com.example.board.orders.converter;

import com.example.board.orders.db.OrderItemEntity;
import com.example.board.orders.db.OrdersEntity;

import com.example.board.orders.model.OrderDto;

import com.example.board.orders.model.OrderItemDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderConverter {

    public OrderDto toDto(OrdersEntity entity) {
        var items = entity.getOrderItems().stream()
            .map(this::toDto)
            .collect(Collectors.toList());

        var totalAmount = items.stream()
            .mapToInt(OrderItemDto::getAmount)
            .sum();

        return OrderDto.builder()
            .id(entity.getId())
            .customerName(entity.getCustomer().getName())
            .customerPhone(entity.getCustomer().getPhone())
            .address(entity.getCustomer().getAddress())
            .status(entity.getStatus())
            .orderedAt(entity.getOrderedAt())
            .items(items)
            .totalAmount(totalAmount)
            .build();
    }

    private OrderItemDto toDto(OrderItemEntity entity) {
        return OrderItemDto.builder()
            .id(entity.getId())
            .bookId(entity.getBook().getId())
            .bookName(entity.getBook().getBookName())
            .quantity(entity.getQuantity())
            .price(entity.getPrice())
            .amount(entity.getPrice() * entity.getQuantity())
            .build();
    }
}