package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.common.Converter;
import kitri.dev6.memore.dto.common.Pagination;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.ArticleRequestDto;
import kitri.dev6.memore.dto.response.ArticleResponseDto;
import kitri.dev6.memore.repository.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public PagingResponse<ArticleResponseDto> findAll(SearchDto params){
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = articleMapper.count();
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<Article> list = articleMapper.findAll(params);
        // domain -> dto
        List<ArticleResponseDto> convertedList = (List<ArticleResponseDto>) (Object) Converter.domainListTodtoList(list);

        return new PagingResponse<>(convertedList, pagination);
    }

    public ArticleResponseDto findById(Long id) {
        Article article = articleMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다, id=" + id));
        return new ArticleResponseDto(article);
    }

    public Long insert(ArticleRequestDto articleRequestDto) {
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
