package com.example.board.orders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.board.post.db.PostEntity;
import com.example.board.orders.service.OrderInquiryService;
import com.example.board.orders.model.OrderInquiryRequest;

@RestController
@RequestMapping("/api/order-inquiry")
@RequiredArgsConstructor
public class OrderInquiryController {
    
    private final OrderInquiryService orderInquiryService;
    
    @PostMapping
    public PostEntity createInquiry(@RequestBody OrderInquiryRequest request) {
        return orderInquiryService.createOrderInquiry(request);
    }
    
    @GetMapping("/order/{orderId}")
    public List<PostEntity> getInquiriesByOrder(@PathVariable Long orderId) {
        return orderInquiryService.getInquiriesByOrder(orderId);
    }
}