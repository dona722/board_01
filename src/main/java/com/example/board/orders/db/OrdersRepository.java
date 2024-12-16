package com.example.board.orders.db;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    // 고객별 주문 목록 조회
    List<OrdersEntity> findByCustomerId(Long customerId);
    
    // 주문 상태별 조회
    List<OrdersEntity> findByStatus(String status);
}