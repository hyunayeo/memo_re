package kitri.dev6.memore.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {

    private int page;             // 현재 페이지 번호
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private int pageSize;         // 화면 하단에 출력할 페이지 사이즈
    private String sortFieldType;     // 정렬 기준
    private String sortAs;    // 정렬 순서
    private String keyword;       // 검색 키워드
    private String searchType;    // 검색 유형 (제목, 아이디, 날짜...)
    private String domainType;
    private Pagination pagination; // 페이지네이션 정보

    public SearchDto() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
        this.sortFieldType = Type.ID;
        this.sortAs = Type.DESC;
    }

    public int getOffset() {
        return (page - 1) * recordSize;
    }
}