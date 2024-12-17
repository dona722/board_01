package com.example.board.orders.controller;

import com.example.board.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderViewController {
    
    private final OrderService orderService;
    
    @GetMapping("")
    public String orderList(Model model) {
        var orders = orderService.getAllOrders();  // OrderService에 이 메소드 필요
        model.addAttribute("orders", orders);
        return "orders/list";
    }
}