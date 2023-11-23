package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.ArticleRequest;
import kitri.dev6.memore.repository.ArticleMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    @Autowired
    private ArticleMapper articleMapper;


    @PostMapping("/list")
    public List<Article> list(){
        return articleMapper.findAll();
    }


    @PostMapping()
    public Long create(@RequestBody ArticleRequest articleRequest) {
        if (articleRequest != null) {
            Article article = new Article();
            article.setTitle(articleRequest.getTitle());
            article.setContent(articleRequest.getContent());
            article.setDone(articleRequest.isDone());
            article.setRatingScore(articleRequest.getRatingScore());
            article.setStartDate(articleRequest.getStartDate());
            article.setEndDate(articleRequest.getEndDate());
            article.setHide(articleRequest.isHide());
            return articleMapper.insert(article);
        }
        return null;
    }


    @GetMapping("/{id}")
    public Article findById(@PathVariable String id){
        return articleMapper.findById(Long.parseLong(id));
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable String id, @RequestBody ArticleRequest articleRequest){
        Article article = articleMapper.findById(Long.parseLong(id));
        System.out.println(article.getId());
        if (article == null) return null;
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setDone(articleRequest.isDone());
        article.setRatingScore(articleRequest.getRatingScore());
        article.setStartDate(articleRequest.getStartDate());
        article.setEndDate(articleRequest.getEndDate());
        article.setHide(articleRequest.isHide());

        return articleMapper.update(article);
    }

    @GetMapping("/delete/{id}")
    public Long delete(@PathVariable String id){
        return articleMapper.delete(Long.parseLong(id));
    }


}
