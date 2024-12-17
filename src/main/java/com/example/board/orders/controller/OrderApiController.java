package com.example.board.orders.controller;

import com.example.board.orders.model.OrderRequest;
import com.example.board.orders.model.OrderDto;
import com.example.board.orders.service.OrderService;
import com.example.board.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderApiController {
    
    private final OrderService orderService;
    
    @PostMapping("")
    public ApiResponse<OrderDto> createOrder(@RequestBody OrderRequest request) {
        // 비밀번호 유효성 검사
        if (!request.getPassword().matches("\\d{4}")) {
            throw new RuntimeException("비밀번호는 4자리 숫자여야 합니다.");
        }
        
        var order = orderService.createOrder(request);
        return ApiResponse.ok(order);
    }
    @GetMapping("/{orderId}")
    public ApiResponse<OrderDto> getOrder(
        @PathVariable Long orderId,
        @RequestParam String password
    ) {
        return ApiResponse.ok(orderService.getOrderWithPassword(orderId, password));
    }
        
}