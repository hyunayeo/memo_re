package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.common.Pagination;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.ArticleRequestDto;
import kitri.dev6.memore.repository.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public PagingResponse<Article> findAll(SearchDto params){
        params.setDomainType("article");
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = articleMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<Article> list = articleMapper.findAllWithBookAndMember(params);

        return new PagingResponse<>(list, pagination);
    }

//    private List<Article> findAllFetchJoin(SearchDto params) {
//        List<Article> result = null;
//        if (!params.getFetchJoin().isEmpty()) {
//            if (params.getFetchJoin().equals("true")) {
//                articleMapper.findAllFetchJoin(params);
//            }
//        }
//          result = articleMapper.findAll(params);
//
//        return null;
//    }

    public PagingResponse<Article> findByBookId(SearchDto params, Long bookId) {
        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<Article> list = articleMapper.findByBookId(bookId);
        // domain -> dto
//        List<ArticleResponseDto> convertedList = (List<ArticleResponseDto>) (Object) Converter.domainListTodtoList(list);
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = list.size();
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        return new PagingResponse<>(list, pagination);
    }

    public PagingResponse<Article> findByMemberId(SearchDto params, Long memberId) {
        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<Article> list = articleMapper.findByMemberId(memberId);
        // domain -> dto
//        List<ArticleResponseDto> convertedList = (List<ArticleResponseDto>) (Object) Converter.domainListTodtoList(list);
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = list.size();
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        return new PagingResponse<>(list, pagination);
    }

    public Article findById(Long id) {
        Article article = articleMapper.findById(id);
        if (article!=null){
            article.setViewCount(article.getViewCount()+1);
            articleMapper.updateViewCount(id);
            return article;
        }
        return null;
    }

    public Long insert(ArticleRequestDto articleRequestDto) {
        Article article = articleRequestDto.toDomain();
        article.updateEndDate();
        articleMapper.insert(article);
        return article.getId();
    }

    public Long update(Long id, ArticleRequestDto articleRequestDto) {
        Article article = articleMapper.findById(id);
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
        article.updateEndDate();
        return articleMapper.updateById(article);
    }

    public void delete(Long memberId, Long id) {
        Article article = articleMapper.findById(id);
        if (article != null & article.getMember().getId().equals(memberId)) {
            articleMapper.deleteById(id);
        }
    }
}
