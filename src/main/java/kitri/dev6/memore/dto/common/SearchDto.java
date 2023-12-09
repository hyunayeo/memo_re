package kitri.dev6.memore.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
    public static final String FROM_DB = "DB";
    public static final String FROM_NAVER = "NAVER";
    public static final String FROM_ALADIN = "ALADIN";

    // 알라딘 도서 검색 : Sort 변수 종류
//    Accuracy(기본값): 관련도
//    PublishTime : 출간일
//    Title : 제목
//    SalesPoint : 판매량
//    CustomerRating 고객평점
//    MyReviewCount :마이리뷰갯수
    // 알라딘 도서 검색 : Cover 사이즈 변수 종류
//    Big : 큰 크기 : 너비 200px
//    MidBig : 중간 큰 크기 : 너비 150px
//    Mid(기본값) : 중간 크기 : 너비 85px
//    Small : 작은 크기 : 너비 75px
//    Mini : 매우 작은 크기 : 너비 65px
//    None : 없음

    private int page;             // 현재 페이지 번호
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private int pageSize;         // 화면 하단에 출력할 페이지 사이즈
    private String domainType;
    private String sortType;     // 정렬 기준
    private String sortAs;          // 정렬 순서
    private String searchKeyword;       // 검색 키워드
    private String searchType;    // 검색 유형 (제목, 아이디, 날짜...)
    private Pagination pagination; // 페이지네이션 정보
    private String joinWith;
    private String apiFrom;

    public SearchDto() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
        this.sortType = Type.ID;
        this.sortAs = Type.DESC;
        this.searchType = "";
        this.searchKeyword = "";
        this.apiFrom = FROM_NAVER;
    }

    public int getOffset() {
        return (page - 1) * recordSize;
    }

    // openAPI를 써야하는지
    public boolean shouldIUseNaverBookApi() {
        if (!this.getDomainType().equals("book")) {
            return false;
        }
        if (this.getSearchType().equals("title") ||
                this.getSearchType().equals("author")) {
            return true;
        }
        return false;
    }

    public boolean shouldIUseAladinBookApi() {
        if (!this.getDomainType().equals("book")) {
            return false;
        }
        if (this.getSearchType().equals("ItemNewAll") ||
                this.getSearchType().equals("neItemNewSpecial")
                    || this.getSearchType().equals("ItemEditorChoice")
                        || this.getSearchType().equals("Bestseller")
                            || this.getSearchType().equals("BlogBest "))
        {
            return true;
        }
        return false;
    }

    public void setPaging(int count) {
        this.setPagination(new Pagination(count, this));
    }
}