package kitri.dev6.memore.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Builder
public class BookUpdateRequestDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String isbn;
    private String cover;
    private String link;
    private String description;
    @NotEmpty
    private String author;
    @NotEmpty
    private String publisher;
    private LocalDate publishedDate;
    @NotEmpty
    private Boolean approved;
}
