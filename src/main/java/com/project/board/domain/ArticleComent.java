package com.project.board.domain;

import java.time.LocalDateTime;

public class ArticleComent {

    private Long id;
    private Article article; // 게시글 아이디
    private String contect;  // 본문

    private LocalDateTime createdAt; // 생성일시
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정일시
    private String modifiedBy; // 수정자
}
