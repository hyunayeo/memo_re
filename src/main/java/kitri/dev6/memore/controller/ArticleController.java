package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.ArticleRequestDto;
import kitri.dev6.memore.dto.ArticleResponseDto;
import kitri.dev6.memore.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    @GetMapping
    public List<Article> list(@RequestParam(name = "memberId", required = false) Long memberId) {
        return articleService.list(memberId);
    }
    @PostMapping
    public Long insert(@RequestBody ArticleRequestDto articleRequestDto) {
        return articleService.insert(articleRequestDto);
    }
    @GetMapping("/{id}")
    public ArticleResponseDto findById(@PathVariable Long id) {
        return articleService.findById(id);
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
