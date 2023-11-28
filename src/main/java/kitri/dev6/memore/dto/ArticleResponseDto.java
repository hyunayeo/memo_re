package kitri.dev6.memore.dto;

import kitri.dev6.memore.domain.Article;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ArticleResponseDto {
    private Long id;
    private Long memberId;
    private Long bookId;
    private String title;
    private String content;
    private int viewCount;
    private boolean isDone;
    private LocalDate startDate;
    private LocalDate endDate;
    private int ratingScore;
    private boolean isHide;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

    public ArticleResponseDto(Article domain){
        this.id = domain.getId();
        this.memberId = domain.getMemberId();
        this.bookId = domain.getBookId();
        this.title = domain.getTitle();
        this.content = domain.getContent();
        this.viewCount = domain.getViewCount();
        this.isDone = domain.isDone();
        this.startDate = domain.getStartDate();
        this.endDate = domain.getEndDate();
        this.ratingScore = domain.getRatingScore();
        this.isHide = domain.isHide();
        this.createdAt = domain.getCreatedAt();
        this.modifiedAt = domain.getModifiedAt();
        this.deletedAt = domain.getDeletedAt();
    }
}

