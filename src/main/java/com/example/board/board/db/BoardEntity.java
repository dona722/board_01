package com.example.board.board.db;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;

    private String status;

    @OneToMany(
            mappedBy = "boardEntity"
    )
    @Builder.Default
    @Where(clause = "status = 'REGISTERED'")
    @OrderBy("id desc")
    private List<PostEntity> postList = List.of();

}
