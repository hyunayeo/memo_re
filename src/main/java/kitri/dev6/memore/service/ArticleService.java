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

    public Long create(ArticleRequestDto articleRequestDto) {
        Article article = articleRequestDto.toDomain();
        articleMapper.insert(article);
        return article.getId();
    }

    public Long update(Long id, ArticleRequestDto articleRequestDto) {
        Article article = articleMapper.findById(id);
        if (article == null) return null;
        article.setMemberId(articleRequestDto.getMemberId());
        article.setBookId(articleRequestDto.getBookId());
        article.setTitle(articleRequestDto.getTitle());
        article.setContent(articleRequestDto.getContent());
        article.setDone(articleRequestDto.isDone());
        article.setRatingScore(articleRequestDto.getRatingScore());
        article.setStartDate(articleRequestDto.getStartDate());
        article.setEndDate(articleRequestDto.getEndDate());
        article.setHide(articleRequestDto.isHide());
        return articleMapper.updateById(article);
    }

    public Long delete(String id) {
        return articleMapper.delete(Long.parseLong(id));
    }
}
