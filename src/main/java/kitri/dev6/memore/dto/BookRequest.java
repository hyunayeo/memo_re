package kitri.dev6.memore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
public class BookRequest {
    @NotEmpty
    private Long categoryId;
    @NotEmpty
    private Long memberId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String isbn;
    @NotEmpty
    private String isbn13;
    private String cover;
    private String link;
    private String description;
    @NotEmpty
    private String author;
    @NotEmpty
    private String publisher;
    private LocalDate publishedDate;
    @NotEmpty
    private boolean approved;
}
