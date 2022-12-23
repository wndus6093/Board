package com.project.board.controller;

import com.project.board.config.SecurityConfig;
import com.project.board.dto.ArticleWithCommentsDto;
import com.project.board.dto.UserAccountDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import service.ArticleService;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.AdditionalMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    private final MockMvc mvc;

    @MockBean private ArticleService articleService;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }


    @DisplayName("[view] [GET] 게시글 리스트 (게시판) 페이지 - 정상호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception{
        BDDMockito.given(articleService.searchArticles(ArgumentMatchers.eq(null) , ArgumentMatchers.eq(null), ArgumentMatchers.any(Pageable.class))).willReturn(Page.empty());

        mvc.perform(get("/article"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("article/datail"))
                .andExpect(model().attributeExists("articles"));
        BDDMockito.then(articleService).should().searchArticles(ArgumentMatchers.eq(null) , ArgumentMatchers.eq(null), ArgumentMatchers.any(Pageable.class));}


    @DisplayName("[view] [GET] 게시글 상세페이지 - 정상호출")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception{
        //Given
        Long articleId = 1L;
        BDDMockito.given(articleService.getArticle(articleId)).willReturn(createArticleWithCommentsDto());
        //Then
        mvc.perform(get("/article/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("article"));
        BDDMockito.then(articleService).should().getArticle(articleId);
    }


    @Disabled("구현중")
    @DisplayName("[view] [GET] 게시글 검색 전용 페이지 - 정상호출")
    @Test
    public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception{

        mvc.perform(get("/article/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles/search"));
    }

    @Disabled("구현중")
    @DisplayName("[view] [GET] 게시글 해쉬태그 검색 페이지 - 정상호출")
    @Test
    public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception{

        mvc.perform(get("/article/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles/search-hashtag"));
    }

    private ArticleWithCommentsDto createArticleWithCommentsDto(){
        return ArticleWithCommentsDto.of(
                1L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                "#java",
                LocalDateTime.now(),
                "wndus",
                LocalDateTime.now(),
                "wndus"
        );

    }private UserAccountDto createUserAccountDto(){
        return UserAccountDto.of(
                "wndus",
                "pw",
                "wndus@mail.com",
                "wndus",
                "memo",
                LocalDateTime.now(),
                "wndus",
                LocalDateTime.now(),
                "wndus"
        );
    }
}
