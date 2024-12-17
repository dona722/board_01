package com.example.board.orders.service;

import com.example.board.book.db.BookRepository;
import com.example.board.customer.db.CustomerEntity;
import com.example.board.customer.db.CustomerRepository;
import com.example.board.orders.db.OrderItemEntity;
import com.example.board.orders.db.OrdersEntity;
import com.example.board.orders.db.OrdersRepository;
import com.example.board.orders.model.OrderDto;
import com.example.board.orders.model.OrderRequest;
import com.example.board.orders.converter.OrderConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final OrderConverter orderConverter;

    @Transactional
    public OrderDto createOrder(OrderRequest request) {
        // 1. 고객 정보 생성 또는 조회
        CustomerEntity customer = customerRepository.findByPhone(request.getCustomerPhone())
            .orElseGet(() -> customerRepository.save(
                CustomerEntity.builder()
                    .name(request.getCustomerName())
                    .phone(request.getCustomerPhone())
                    .address(request.getAddress())
                    .build()
            ));

        // 2. 문 상품 재고 확인
        request.getItems().forEach(item -> {
            var book = bookRepository.findById(item.getBookId())
                .orElseThrow(() -> new RuntimeException("도서를 찾을 수 없습니다."));
            
            if (book.getStock() < item.getQuantity()) {
                throw new RuntimeException("재고가 부족합니다.");
            }
        });

        // 3. 주문 생성
        var order = OrdersEntity.builder()
            .customer(customer)
            .status("ORDERED")
            .orderedAt(LocalDateTime.now())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

        // 4. 주문 상품 추가 및 재고 감소
        var orderItems = request.getItems().stream()
            .map(item -> {  // OrderItemRequest로 타입 명시 제거
                var book = bookRepository.findById(item.getBookId()).get();
                book.setStock(book.getStock() - item.getQuantity());
                bookRepository.save(book);

                return OrderItemEntity.builder()
                    .order(order)
                    .book(book)
                    .quantity(item.getQuantity())
                    .price(book.getPrice())  // orderPrice -> price로 수정
                    .build();
            })
            .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        
        // 5. 주문 저장
        var savedOrder = ordersRepository.save(order);
        return orderConverter.toDto(savedOrder);
    }

    // 비밀번호로 주문 조회 메소드 추가
    public OrderDto getOrderWithPassword(Long orderId, String password) {
        var order = ordersRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
            
        if (!order.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        
        return orderConverter.toDto(order);
    }

    public OrderDto getOrder(Long orderId) {
        var order = ordersRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
        return orderConverter.toDto(order);
    }

    @Transactional
    public OrderDto updateOrderStatus(Long orderId, String status) {
        var order = ordersRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
        
        order.setStatus(status);
        return orderConverter.toDto(ordersRepository.save(order));
    }

    public List<OrderDto> getAllOrders() {
        return ordersRepository.findAllByOrderByIdDesc()
            .stream()
            .map(orderConverter::toDto)
            .collect(Collectors.toList());
    }
}