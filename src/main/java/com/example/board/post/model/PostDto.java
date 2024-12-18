package com.example.board.post.model;

import com.example.board.post.db.InquiryStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {

    private Long id;
    private Long boardId;
    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;
    private String content;
    private LocalDateTime postedAt;
    private LocalDateTime updatedAt;
    private InquiryStatus inquiryStatus;
    private boolean secret;  // Entity의 isSecret과 매핑
    
    // 필요한 경우 order, book 관련 필드 추가
    private Long orderId;
    private Long bookId;
}
