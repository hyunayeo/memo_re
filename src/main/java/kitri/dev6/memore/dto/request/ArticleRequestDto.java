package kitri.dev6.memore.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import kitri.dev6.memore.domain.Article;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequestDto {
    @NotEmpty
    private Long memberId;
    @NotEmpty
    private Long bookId;
    @NotEmpty
    private String title;
    private String content;
    @NotEmpty @JsonProperty("isDone")
    private boolean isDone;
    @NotEmpty
    private LocalDate startDate;
    private LocalDate endDate;
    private int ratingScore;
    @NotEmpty @JsonProperty("isHide")
    private boolean isHide;

    public Article toDomain(){
        return Article.builder()
                .memberId(memberId)
                .bookId(bookId)
                .title(title)
                .content(content)
                .isDone(isDone)
                .startDate(startDate)
                .endDate(endDate)
                .ratingScore(ratingScore)
                .isHide(isHide)
                .build();
    }
}
