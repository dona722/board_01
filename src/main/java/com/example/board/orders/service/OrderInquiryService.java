package com.example.board.orders.service;

import com.example.board.board.db.BoardEntity;
import com.example.board.board.db.BoardRepository;
import com.example.board.board.db.BoardType;
import com.example.board.orders.db.OrderItemEntity;
import com.example.board.orders.db.OrdersEntity;
import com.example.board.orders.db.OrdersRepository;
import com.example.board.post.db.InquiryStatus;
import com.example.board.post.db.PostEntity;
import com.example.board.post.db.PostRepository;
import com.example.board.orders.model.OrderInquiryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderInquiryService {
    
    private final PostRepository postRepository;
    private final OrdersRepository ordersRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public PostEntity createOrderInquiry(OrderInquiryRequest request) {
        // 1. 주문 정보 확인
        OrdersEntity order = ordersRepository.findById(request.getOrderId())
            .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
            
        // 2. 문의 게시판 확인
        BoardEntity inquiryBoard = boardRepository.findByBoardType(BoardType.ORDER_INQUIRY)
            .orElseThrow(() -> new RuntimeException("문의 게시판을 찾을 수 없습니다."));

        // 3. 문의글 작성
        return postRepository.save(PostEntity.builder()
            .boardEntity(inquiryBoard)
            .order(order)
            .book(request.getBookId() != null ? 
                order.getOrderItems().stream()
                    .filter(item -> item.getBook().getId().equals(request.getBookId()))
                    .findFirst()
                    .map(OrderItemEntity::getBook)
                    .orElse(null) : null)
            .title(request.getTitle())
            .content(request.getContent())
            .userName(order.getCustomer().getName())
            .status("REGISTERED")
            .inquiryStatus(InquiryStatus.PENDING)
            .postedAt(LocalDateTime.now())
            .build());
    }

    public List<PostEntity> getInquiriesByOrder(Long orderId) {
        return postRepository.findByOrderIdAndStatus(orderId, "REGISTERED");
    }
}