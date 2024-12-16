package com.example.board.orders.controller;

import com.example.board.common.api.ApiResponse;
import com.example.board.orders.model.OrderDto;
import com.example.board.orders.model.OrderRequest;
import com.example.board.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderDto> createOrder(@Valid @RequestBody OrderRequest request) {
        return ApiResponse.success(orderService.createOrder(request));
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderDto> getOrder(@PathVariable Long orderId) {
        return ApiResponse.success(orderService.getOrder(orderId));
    }

    @PutMapping("/{orderId}/status")
    public ApiResponse<OrderDto> updateOrderStatus(
        @PathVariable Long orderId,
        @RequestParam String status
    ) {
        return ApiResponse.success(orderService.updateOrderStatus(orderId, status));
    }
}