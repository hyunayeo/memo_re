package kitri.dev6.memore.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.open_api.AladinApiResponseDto;
import kitri.dev6.memore.dto.open_api.AladinBookVO;
import kitri.dev6.memore.dto.open_api.NaverApiResponseDto;
import kitri.dev6.memore.dto.open_api.NaverBookVO;
import kitri.dev6.memore.repository.BookMapper;
import kitri.dev6.memore.repository.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookOpenApiService {

    final CategoryMapper categoryMapper;
    String NAVER_CLIENT_ID = "COtAbIwgRLBncek_NYIl";
    String CLIENT_SECRET = "ChApSR2iDe";
    String ALADIN_SECRET = "ttbpicnic9351807001";

    List<NaverBookVO> fetchAllFromNaver(SearchDto params) {

        //String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;    // JSON 결과
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", params.getSearchKeyword())
                .queryParam("display", params.getRecordSize())
                .queryParam("start", params.getOffset() + 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        // Spring 요청 제공 클래스
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", NAVER_CLIENT_ID)
                .header("X-Naver-Client-Secret", CLIENT_SECRET)
                .build();
        // Spring 제공 restTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        NaverApiResponseDto result = null;
        try {
            result = om.readValue(resp.getBody(), NaverApiResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        params.setPaging(result.getTotal());

        return result.getItems();
    }

    List<AladinBookVO> fetchAllFromAladin(SearchDto params) {

        URI uri = UriComponentsBuilder
                .fromUriString("https://aladin.co.kr")
                .path("/ttb/api/ItemList.aspx")
                .queryParam("ttbkey", ALADIN_SECRET)
                .queryParam("QueryType", params.getSearchType())
                .queryParam("MaxResults", params.getRecordSize())
                .queryParam("start", params.getOffset() + 1)
                .queryParam("Cover", "Big")
                .queryParam("SearchTarget", "Book")
                .queryParam("output","js")
                .queryParam("Version", 20131101)
//                .queryParam("Sort", params.getSortFieldType())
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(RequestEntity.get(uri).build(), String.class);

        // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        AladinApiResponseDto result = null;
        try {
            result = om.readValue(resp.getBody(), AladinApiResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        params.setPaging(result.getTotalResults());

        return result.getItem();
    }

    List<AladinBookVO> searchAllFromAladin(SearchDto params) {

        URI uri = UriComponentsBuilder
                .fromUriString("https://aladin.co.kr")
                .path("/ttb/api/ItemSearch.aspx")
                .queryParam("ttbkey", ALADIN_SECRET)
                .queryParam("Query", params.getSearchKeyword())
                .queryParam("QueryType", params.getSearchType())
                .queryParam("MaxResults", params.getRecordSize())
                .queryParam("start", params.getOffset() + 1)
                .queryParam("SearchTarget", "Book")
                .queryParam("output","js")
                .queryParam("Version", 20131101)
//                .queryParam("Sort", params.getSortFieldType())
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(RequestEntity.get(uri).build(), String.class);

        // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        AladinApiResponseDto result = null;
        try {
            result = om.readValue(resp.getBody(), AladinApiResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        params.setPaging(result.getTotalResults());

        return result.getItem();
    }

    Book fetchOneFromAladin(String isbn) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString("https://aladin.co.kr")
                .path("/ttb/api/ItemLookUp.aspx")
                .queryParam("ttbkey", ALADIN_SECRET)
                .queryParam("itemIdType", "ISBN")
                .queryParam("ItemId", isbn)
                .queryParam("Cover", "Big")
                .queryParam("output", "js")
                .queryParam("Version", "20131101")
                .queryParam("OptResult","packing")
                .encode()
                .build()
                .toUri();

        AladinApiResponseDto response = restTemplate.getForObject(uri, AladinApiResponseDto.class);
        AladinBookVO v = response.getItem().get(0);

        // 카테고리 매핑 시키기
        v.setCategoryId(categoryMapper.findCategoryId(v.getCategoryId()));

        return new Book(v);
    }


}
