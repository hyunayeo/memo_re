package kitri.dev6.memore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ArticleRequestDto {

    @NotEmpty
    private String title;
    private String content;
    @NotEmpty
    private boolean done;
    @NotEmpty
    private LocalDate startDate;
    private LocalDate endDate;
    private int ratingScore;
    @NotEmpty
    private boolean isHide;
}
