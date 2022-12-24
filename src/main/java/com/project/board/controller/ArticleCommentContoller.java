package com.project.board.controller;

import com.project.board.dto.UserAccountDto;
import com.project.board.dto.request.ArticleCommentRequest;
import com.project.board.dto.request.ArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ArticleCommentService;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentContoller {

    private final ArticleCommentService articleCommentService;

    @PostMapping ("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {
        //TODO : 인증 정보 넣어주기
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
                "wndus","pw","wndus@mail.com", null, null
        )));

        return "redirect:/articles/" + articleCommentRequest.articleId();
    }
    @PostMapping ("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, Long articleId) {
        articleCommentService.deleteArticleComment(commentId);

        return "redirect:/articles/" + articleId;
    }

}
