package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.repository.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private ArticleMapper articleMapper;

    public List<Article> articleList(){
        return articleMapper.findAll();
    }

    public Article findById(Long articleId){
        return articleMapper.findById(articleId);
    }

    public Long update(Long id){
        Article article = articleMapper.findById(id);
        if (article == null) return null;
        return articleMapper.update(article);
    }

    public Long delete(Long articleId){
        return articleMapper.delete(articleId);
    }
}
