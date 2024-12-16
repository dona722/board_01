package com.example.board.board.service;

import com.example.board.board.db.BoardEntity;
import com.example.board.board.db.BoardRepository;
import com.example.board.board.db.BoardType;
import com.example.board.board.model.BoardDto;
import com.example.board.board.model.BoardRequest;
import com.example.board.post.db.PostEntity;
import com.example.board.post.db.PostRepository;
import com.example.board.post.model.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final BoardConverter boardConverter;

    // 게시판 생성
    public BoardDto create(BoardRequest boardRequest) {
        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        var savedEntity = boardRepository.save(entity);
        return boardConverter.toDto(savedEntity);
    }

    // 게시판 조회
    public BoardDto get(Long id) {
        var entity = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));
        return boardConverter.toDto(entity);
    }

    // 게시판 목록 조회
    public List<BoardDto> getAll() {
        return boardRepository.findAll().stream()
                .map(boardConverter::toDto)
                .collect(Collectors.toList());
    }

    // PostEntity를 PostDto로 변환하는 메서드 추가
    private PostDto convertToPostDto(PostEntity entity) {
        return PostDto.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .userName(entity.getUserName())
            .postedAt(entity.getPostedAt())
            .build();
    }

    // 공지사항 조회 (최근 n개)
    public List<PostDto> getNotices(int limit) {
        return postRepository.findByBoardEntityBoardTypeAndStatusOrderByIdDesc(
                BoardType.NOTICE,
                "REGISTERED",
                PageRequest.of(0, limit)
        ).stream()
        .map(this::convertToPostDto)  // boardConverter::toDto 대신 사용
        .collect(Collectors.toList());
    }

    // 문의글 조회 (최근 n개)
    public List<PostDto> getInquiries(int limit) {
        return postRepository.findByBoardEntityBoardTypeAndStatusOrderByIdDesc(
                BoardType.ORDER_INQUIRY,
                "REGISTERED",
                PageRequest.of(0, limit)
        ).stream()
        .map(this::convertToPostDto)
        .collect(Collectors.toList());
    }

    // 게시판 삭제 (상태 변경)
    @Transactional
    public void delete(Long id) {
        var entity = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));
        
        entity.setStatus("DELETED");
        boardRepository.save(entity);
    }

    // 게시판의 게시글 목록 조회
    public List<PostDto> getBoardPosts(Long boardId) {
        return postRepository.findByBoardEntityIdAndStatus(boardId, "REGISTERED")
            .stream()
            .map(this::convertToPostDto)
            .collect(Collectors.toList());
    }
}