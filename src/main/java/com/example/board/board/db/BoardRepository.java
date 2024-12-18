package com.example.board.board.db;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Optional<BoardEntity> findByBoardType(BoardType boardType);

    // 최근 공지사항 조회
    List<BoardEntity> findByBoardTypeOrderByIdDesc(BoardType boardType);
}
