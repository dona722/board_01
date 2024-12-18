package com.example.board.orders.service;

import com.example.board.book.db.BookRepository;
import com.example.board.customer.db.CustomerEntity;
import com.example.board.customer.db.CustomerRepository;
import com.example.board.orders.converter.OrderConverter;
import com.example.board.orders.db.OrderItemEntity;
import com.example.board.orders.db.OrdersEntity;
import com.example.board.orders.db.OrdersRepository;
import com.example.board.orders.model.OrderDto;
import com.example.board.orders.model.OrderRequest;
import com.example.board.orders.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final OrderConverter orderConverter;

    // 주문 목록 조회
    public List<OrderDto> getOrderList() {
        return ordersRepository.findAllByOrderByIdDesc().stream()
            .map(orderConverter::toDto)
            .collect(Collectors.toList());
    }

    // 주문 상세 조회 (비밀번호 확인)
    public OrderDto getOrderWithPassword(Long id, String password) {
        var order = ordersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
            
        if (!order.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        
        return orderConverter.toDto(order);
    }

    // 주문 생성
    @Transactional
    public OrderDto createOrder(OrderRequest request) {
        // 고객 정보 생성 또는 조회
        var customer = createOrGetCustomer(request);
        
        // 주문 엔티티 생성
        var order = OrdersEntity.builder()
            .customer(customer)
            .status(OrderStatus.ORDERED.name())
            .password(request.getPassword())
            .build();
            
        // 주문 항목 추가
        request.getItems().forEach(item -> {
            var book = bookRepository.findById(item.getBookId())
                .orElseThrow(() -> new RuntimeException("도서를 찾을 수 없습니다."));
                
            var orderItem = OrderItemEntity.builder()
                .order(order)
                .book(book)
                .quantity(item.getQuantity())
                .price(book.getPrice())
                .build();
                
            order.addOrderItem(orderItem);
        });
        
        // 총 주문 금액 계산
        order.calculateTotalAmount();
        
        // 주문 저장
        var savedOrder = ordersRepository.save(order);
        return orderConverter.toDto(savedOrder);
    }

    // 주문 상태 변경
    @Transactional
    public OrderDto updateOrderStatus(Long id, String status, String password) {
        var order = ordersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
            
        if (!order.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        
        var newStatus = OrderStatus.fromString(status);
        var currentStatus = OrderStatus.fromString(order.getStatus());
        
        if (!currentStatus.isChangeable()) {
            throw new RuntimeException("현재 상태에서는 변경할 수 없습니다.");
        }
        
        order.setStatus(newStatus.name());
        var savedOrder = ordersRepository.save(order);
        return orderConverter.toDto(savedOrder);
    }

    // 고객 정보 생성 또는 조회
    private CustomerEntity createOrGetCustomer(OrderRequest request) {
        return customerRepository.findByPhone(request.getCustomerPhone())
            .orElseGet(() -> customerRepository.save(
                CustomerEntity.builder()
                    .name(request.getCustomerName())
                    .phone(request.getCustomerPhone())
                    .address(request.getAddress())
                    .build()
            ));
    }

    public OrderDto findByCustomerNameAndPassword(String customerName, String password) {
        var order = ordersRepository.findFirstByCustomer_NameAndPasswordOrderByIdDesc(customerName, password)
            .orElseThrow(() -> new RuntimeException("주문 정보를 찾을 수 없습니다."));
        return orderConverter.toDto(order);
    }

}