package kitri.dev6.memore.controller;

import io.swagger.v3.oas.annotations.Operation;
import kitri.dev6.memore.configuration.auth.LoginUser;
import kitri.dev6.memore.configuration.auth.dto.SessionUser;
import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.ArticleRequestDto;
import kitri.dev6.memore.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    @GetMapping
    @Operation(summary = "게시글 검색", description = "검색어를 이용하여 게시글을 검색합니다.")
    public ResponseEntity<PagingResponse<Article>> findAll(@ModelAttribute("params") SearchDto params) {

        PagingResponse<Article> articles = articleService.findAll(params);
        if (articles == null) {
            throw new IllegalArgumentException("No Articles");
        }
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "게시글 단일 조회", description = "게시글 아이디를 이용하여 게시글을 조회합니다.")
    public ResponseEntity<Article> findById(@PathVariable Long id) {
        Article article = articleService.findById(id);
        if (article == null) {
            throw new IllegalArgumentException("No article");
        }
        return new ResponseEntity<>(article, HttpStatus.OK);
    }
    @PostMapping
    @Operation(summary = "게시글 등록", description = "제목과 내용, 도서정보 등을 입력하여 게시글을 등록합니다.")
    public Long insert(@LoginUser SessionUser user, @RequestBody ArticleRequestDto articleRequestDto) {
        if (user.getId().equals(articleRequestDto.getMemberId())){
            return articleService.insert(articleRequestDto);
        }
        throw new IllegalArgumentException("부적절한 요청");
    }
    @PutMapping("/{id}")
    @Operation(summary = "게시글 수정", description = "게시글 아이디를 이용하여 게시글을 수정합니다.")
    public Long update(@LoginUser SessionUser user, @PathVariable Long id, @RequestBody ArticleRequestDto articleRequestDto) {
        if (user.getId().equals(articleRequestDto.getMemberId())){
            articleService.update(id, articleRequestDto);
            return id;
        }
        throw new IllegalArgumentException("부적절한 요청");
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제", description = "게시글 아이디를 이용하여 게시글을 삭제합니다.")
    public Long delete(@LoginUser SessionUser user, @PathVariable Long id) {
        articleService.delete(user.getId(),id);
        return id;
    }
}
