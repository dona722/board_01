package com.example.board.post.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import com.example.board.board.db.BoardType;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    // select * from post where id = ? and status = ? order by id desc limit 1
    Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);
    List<PostEntity> findByOrderIdAndStatus(Long orderId, String status);

    List<PostEntity> findByBoardEntityBoardTypeAndStatusOrderByIdDesc(
        BoardType boardType, 
        String status, 
        Pageable pageable
    );

    List<PostEntity> findByBoardEntityIdAndStatus(Long boardId, String status);

    List<PostEntity> findByStatus(String status);

    List<PostEntity> findAllByStatusOrderByIdDesc(String status);

    List<PostEntity> findByBoardEntityIdAndStatusOrderByIdDesc(Long boardId, String status);

    List<PostEntity> findByBoardEntityBoardTypeOrderByIdDesc(BoardType boardType);
    
    // 특정 상태의 문의글 조회
    List<PostEntity> findByBoardEntityBoardTypeAndInquiryStatusOrderByIdDesc(
        BoardType boardType, 
        InquiryStatus inquiryStatus
    );

    List<PostEntity> findByBoardEntityBoardTypeAndStatusOrderByIdDesc(BoardType boardType, String status);

    List<PostEntity> findByBoardEntityIdAndStatusOrderByIdDesc(
        Long boardId, 
        String status, 
        Pageable pageable
    );
}
