package com.example.board.orders.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    // 고객별 주문 목록 조회
    List<OrdersEntity> findByCustomerId(Long customerId);
    
    // 주문 상태별 조회
    List<OrdersEntity> findByStatus(String status);

    List<OrdersEntity> findAllByOrderByIdDesc();

    Optional<OrdersEntity> findFirstByCustomerNameAndPasswordOrderByIdDesc(String customerName, String password);


    @Query("SELECT o FROM orders o JOIN o.customer c WHERE c.name = :customerName AND o.password = :password ORDER BY o.id DESC")
    Optional<OrdersEntity> findFirstByCustomer_NameAndPasswordOrderByIdDesc(
        @Param("customerName") String customerName, 
        @Param("password") String password
);}