package kitri.dev6.memore.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Article {
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

    public void update(Long memberId, Long bookId, String title, String content, boolean isDone,
                       LocalDate startDate, LocalDate endDate, int ratingScore, boolean isHide) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.title = title;
        this.content = content;
        this.isDone = isDone;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ratingScore = ratingScore;
        this.isHide = isHide;
    }
}



