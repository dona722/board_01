package com.example.board.orders.db;

import com.example.board.customer.db.CustomerEntity;
import com.example.board.orders.db.OrderItemEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "orders")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Builder.Default
    private List<OrderItemEntity> orderItems = new ArrayList<>();
    
    private String status;  // orderStatus 제거하고 status만 사용

    @Column(length = 4)
    private String password;  // 4자리 비밀번호
    
    private Integer totalAmount;
    
    @Column(name = "order_date")
    @Builder.Default
    private LocalDateTime orderedAt = LocalDateTime.now();
    
    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();;
    
    @Column(name = "updated_at")
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();;
    
    // 주문 항목 추가 메소드
    public void addOrderItem(OrderItemEntity orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    
    // 총 주문 금액 계산 메소드
    public void calculateTotalAmount() {
        this.totalAmount = orderItems.stream()
                .mapToInt(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}