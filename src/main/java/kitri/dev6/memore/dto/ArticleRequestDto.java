package kitri.dev6.memore.dto;

import kitri.dev6.memore.domain.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ArticleRequestDto {
    @NotEmpty
    private Long memberId;
    @NotEmpty
    private Long bookId;
    @NotEmpty
    private String title;
    private String content;
    @NotEmpty
    private boolean isDone;
    @NotEmpty
    private LocalDate startDate;
    private LocalDate endDate;
    private int ratingScore;
    @NotEmpty
    private boolean isHide;

    public Article toDomain(){
        return Article.builder()
                .memberId(this.memberId)
                .bookId(this.bookId)
                .title(this.title)
                .content(this.content)
                .isDone(this.isDone)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .ratingScore(this.ratingScore)
                .isHide(this.isHide)
                .build();
    }
}
