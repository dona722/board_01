package com.example.board.post.db;

import com.example.board.board.db.BoardEntity;
import com.example.board.book.db.BookEntity;
import com.example.board.orders.db.OrdersEntity;
import com.example.board.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity; // board+ _id => board_id

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postedAt;

    @OneToMany(
            mappedBy = "post"
    )
    @Builder.Default
    private List<ReplyEntity> replyList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrdersEntity order;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;
    
    @Column
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private InquiryStatus inquiryStatus;  // 문의상태 추가
    
    @Column(name = "is_secret")
    private boolean isSecret;  // 비밀글 여부 추가
}
