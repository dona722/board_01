package com.example.board.post.service;

import com.example.board.board.db.BoardRepository;
import com.example.board.board.db.BoardType;
import com.example.board.post.db.InquiryStatus;
import com.example.board.post.db.PostEntity;
import com.example.board.post.db.PostRepository;
import com.example.board.post.model.PostDto;
import com.example.board.post.model.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final PostConverter postConverter;

    // 게시글 작성
    public PostDto createPost(PostRequest postRequest) {
        var boardEntity = boardRepository.findById(postRequest.getBoardId())
            .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));

        var entity = PostEntity.builder()
            .boardEntity(boardEntity)
            .userName(postRequest.getUserName())
            .password(postRequest.getPassword())
            .email(postRequest.getEmail())
            .status("REGISTERED")
            .title(postRequest.getTitle())
            .content(postRequest.getContent())
            .postedAt(LocalDateTime.now())
            .build();

        var savedEntity = postRepository.save(entity);
        return postConverter.toDto(savedEntity);
    }

    // 게시글 조회
    public PostDto getPost(Long id) {
        var entity = postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return postConverter.toDto(entity);
    }

    // 게시글 목록 조회
    public List<PostDto> getAllPosts() {
        return postRepository.findAllByStatusOrderByIdDesc("REGISTERED").stream()
            .map(postConverter::toDto)
            .collect(Collectors.toList());
    }

    // 게시글 수정
    public PostDto updatePost(Long id, PostRequest postRequest) {
        var entity = postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 비밀번호 확인
        if (!entity.getPassword().equals(postRequest.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        entity.setTitle(postRequest.getTitle());
        entity.setContent(postRequest.getContent());
        entity.setUpdatedAt(LocalDateTime.now());

        var savedEntity = postRepository.save(entity);
        return postConverter.toDto(savedEntity);
    }

    // 게시글 삭제
    public void deletePost(Long id, String password) {
        var entity = postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 비밀번호 확인
        if (!entity.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        entity.setStatus("DELETED");
        postRepository.save(entity);
    }

    // 특정 게시판의 게시글 목록 조회
    public List<PostDto> getPostsByBoardId(Long boardId) {
        return postRepository.findByBoardEntityIdAndStatusOrderByIdDesc(boardId, "REGISTERED")
            .stream()
            .map(postConverter::toDto)
            .collect(Collectors.toList());
    }

    // 공지사항 목록 조회
    public List<PostEntity> getNotices(int limit) {
        return postRepository.findByBoardEntityBoardTypeOrderByIdDesc(BoardType.NOTICE)
            .stream()
            .limit(limit)
            .collect(Collectors.toList());
    }
    
    // 문의글 목록 조회
    public List<PostEntity> getInquiries(int limit) {
        return postRepository.findByBoardEntityBoardTypeOrderByIdDesc(BoardType.INQUIRY)
            .stream()
            .limit(limit)
            .collect(Collectors.toList());
    }
    
    // 특정 상태의 문의글 조회
    public List<PostEntity> getInquiriesByStatus(InquiryStatus status) {
        return postRepository.findByBoardEntityBoardTypeAndInquiryStatusOrderByIdDesc(
            BoardType.INQUIRY, 
            status
        );
    }

    public List<PostDto> getInquiryList() {
        return postRepository.findByBoardEntityBoardTypeOrderByIdDesc(BoardType.ORDER_INQUIRY)
            .stream()
            .map(postConverter::toDto)
            .collect(Collectors.toList());
    }

    public List<PostDto> getNoticeList() {
        return postRepository.findByBoardEntityBoardTypeOrderByIdDesc(BoardType.NOTICE)
            .stream()
            .map(postConverter::toDto)
            .collect(Collectors.toList());
    }

    public List<PostDto> getOrderInquiryList() {
        return postRepository.findByBoardEntityBoardTypeOrderByIdDesc(BoardType.ORDER_INQUIRY)
            .stream()
            .map(postConverter::toDto)
            .collect(Collectors.toList());
    }

}
