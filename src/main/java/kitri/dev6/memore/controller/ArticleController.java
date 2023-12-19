package kitri.dev6.memore.controller;

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
    public ResponseEntity<PagingResponse<Article>> findAll(@ModelAttribute("params") SearchDto params) {

        PagingResponse<Article> articles = articleService.findAll(params);
        if (articles == null) {
            throw new IllegalArgumentException("No Articles");
        }
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Article> findById(@PathVariable Long id) {
        Article article = articleService.findById(id);
        if (article == null) {
            throw new IllegalArgumentException("No article");
        }
        return new ResponseEntity<>(article, HttpStatus.OK);
    }
    @PostMapping
    public Long insert(@LoginUser SessionUser user, @RequestBody ArticleRequestDto articleRequestDto) {
        if (user.getId().equals(articleRequestDto.getMemberId())){
            return articleService.insert(articleRequestDto);
        }
        throw new IllegalArgumentException("부적절한 요청");
    }
    @PutMapping("/{id}")
    public Long update(@LoginUser SessionUser user, @PathVariable Long id, @RequestBody ArticleRequestDto articleRequestDto) {
        if (user.getId().equals(articleRequestDto.getMemberId())){
            articleService.update(id, articleRequestDto);
            return id;
        }
        throw new IllegalArgumentException("부적절한 요청");
    }
    @DeleteMapping("/{id}")
    public Long delete(@LoginUser SessionUser user, @PathVariable Long id) {
        articleService.delete(user.getId(),id);
        return id;
    }
}
