package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.ArticleRequestDto;
import kitri.dev6.memore.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/list")
    public List<Article> list() { return articleService.list(); }
    @PostMapping()
    public Long create(@RequestBody Article article) {
        return articleService.create(article);
    }
    @GetMapping("/{id}")
    public Article findById(@PathVariable String id) {
        return articleService.findById(id);
    }
    @PutMapping("/{id}")
    public Long update(@PathVariable String id, @RequestBody ArticleRequestDto articleRequestDto) {
        return articleService.update(id, articleRequestDto);
    }
    @GetMapping("/delete/{id}")
    public Long delete(@PathVariable String id) {
        return articleService.delete(id);
    }
}
