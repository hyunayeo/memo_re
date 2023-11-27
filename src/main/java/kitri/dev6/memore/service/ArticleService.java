package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.ArticleRequestDto;
import kitri.dev6.memore.repository.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public List<Article> list() {
        return articleMapper.findAll();
    }

    public Article findById(String id) {
        return articleMapper.findById(Long.parseLong(id));
    }

    public Long create(Article article) {
        if (article != null) {
            article.setMemberId(article.getMemberId());
            article.setBookId(article.getBookId());
            article.setTitle(article.getTitle());
            article.setContent(article.getContent());
            article.setDone(article.isDone());
            article.setRatingScore(article.getRatingScore());
            article.setStartDate(article.getStartDate());
            article.setEndDate(article.getEndDate());
            article.setHide(article.isHide());
            articleMapper.insert(article);
        }
        return null;
    }

    public Long update(String id, ArticleRequestDto articleRequestDto) {
        Article article = articleMapper.findById(Long.parseLong(id));
        System.out.println(article.getId());
        if (article == null) return null;
        article.setTitle(articleRequestDto.getTitle());
        article.setContent(articleRequestDto.getContent());
        article.setDone(articleRequestDto.isDone());
        article.setRatingScore(articleRequestDto.getRatingScore());
        article.setStartDate(articleRequestDto.getStartDate());
        article.setEndDate(articleRequestDto.getEndDate());
        article.setHide(articleRequestDto.isHide());

        return articleMapper.update(article);
    }

    public Long delete(String id) {
        return articleMapper.delete(Long.parseLong(id));
    }
}
