package com.example.board.orders.controller;

import com.example.board.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order-search")
@RequiredArgsConstructor
public class OrderSearchController {
    
    private final OrderService orderService;
    
    @GetMapping("")
    public String searchForm() {
        return "orders/search";
    }
    
    @PostMapping("")
    public String searchOrder(
        @RequestParam String customerName,
        @RequestParam String password,
        Model model
    ) {
        try {
            var order = orderService.findByCustomerNameAndPassword(customerName, password);
            model.addAttribute("order", order);
            model.addAttribute("success", true);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "orders/search";
    }
}