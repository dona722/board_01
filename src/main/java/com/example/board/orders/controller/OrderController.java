package com.example.board.orders.controller;

import com.example.board.orders.model.OrderDto;
import com.example.board.orders.model.OrderRequest;
import com.example.board.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;

    // 주문 목록 페이지
    @GetMapping("")
    public String orderList(Model model) {
        var orders = orderService.getOrderList();
        model.addAttribute("orders", orders);
        return "orders/list";
    }

    // 주문 상세 조회 (비밀번호 확인)
    @GetMapping("/{id}")
    @ResponseBody
    public OrderDto getOrder(
        @PathVariable Long id,
        @RequestParam String password
    ) {
        return orderService.getOrderWithPassword(id, password);
    }

    // 주문 상태 변경
    @PostMapping("/{id}/status")
    @ResponseBody
    public OrderDto updateOrderStatus(
        @PathVariable Long id,
        @RequestParam String status,
        @RequestParam String password
    ) {
        return orderService.updateOrderStatus(id, status, password);
    }

    // 주문 생성
    @PostMapping("/api/orders") 
    @ResponseBody
    public OrderDto createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }
    
}