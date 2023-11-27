package kitri.dev6.memore.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Article {
    private Long id;
    private Long memberId;
    private Long bookId;
    private String title;
    private String content;
    private int viewCount;
    private boolean done;
    private LocalDate startDate;
    private LocalDate endDate;
    private int ratingScore;
    private boolean isHide;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
}



