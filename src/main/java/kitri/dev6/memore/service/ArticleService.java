package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.dto.ArticleRequestDto;
import kitri.dev6.memore.dto.ArticleResponseDto;
import kitri.dev6.memore.dto.MemberRequestDto;
import kitri.dev6.memore.repository.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public List<Article> list() {
        return articleMapper.findAll();
    }

    public ArticleResponseDto findById(Long id) {
        Article article = articleMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다, id=" + id));
        return new ArticleResponseDto(article);
    }

    public List<Article> findByMemberId(Long memberId){
        List<Article> articles =articleMapper.findByMemberId(memberId);
        if (articles == null){
            new IllegalArgumentException("해당 게시물이 존재하지 않습니다, member_id=" + memberId);
        }
        return articleMapper.findByMemberId(memberId);
    }

    public Long create(ArticleRequestDto articleRequestDto) {
        Article article = articleRequestDto.toDomain();
        articleMapper.insert(article);
        return article.getId();
    }

    public Long update(Long id, ArticleRequestDto articleRequestDto) {
        Article article = articleMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다, id=" + id));
        if (article == null) return null;
        article.update(articleRequestDto.getMemberId(),
                articleRequestDto.getBookId(),
                articleRequestDto.getTitle(),
                articleRequestDto.getContent(),
                articleRequestDto.isDone(),
                articleRequestDto.getStartDate(),
                articleRequestDto.getEndDate(),
                articleRequestDto.getRatingScore(),
                articleRequestDto.isHide()
        );
        return articleMapper.updateById(article);
    }

    public void delete(Long id) {
        articleMapper.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        articleMapper.deleteById(id);
    }
}
