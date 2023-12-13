package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.ArticleRequestDto;
import kitri.dev6.memore.dto.response.ArticleResponseDto;
import kitri.dev6.memore.dto.response.MemberResponseDto;
import kitri.dev6.memore.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
    public Long insert(@RequestBody ArticleRequestDto articleRequestDto) {
        return articleService.insert(articleRequestDto);
    }
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArticleRequestDto articleRequestDto) {
        articleService.update(id, articleRequestDto);
        return id;
    }
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        articleService.delete(id);
        return id;
    }
}
