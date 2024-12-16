package com.example.board.orders.db;

import com.example.board.book.db.BookEntity;
import com.example.board.customer.db.CustomerEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "book_id")
    private BookEntity bookEntity;

    private Integer salePrice;

    private LocalDateTime orderDate;


}
