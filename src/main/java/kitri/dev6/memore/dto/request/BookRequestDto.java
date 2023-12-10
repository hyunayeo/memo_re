package kitri.dev6.memore.dto.request;

import kitri.dev6.memore.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
@Getter
@Setter
@Builder
public class BookRequestDto {
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
    public Book toDomain(){
        return Book.builder()
                .categoryId(this.categoryId)
                .memberId(this.memberId)
                .title(this.title)
                .isbn(this.isbn)
                .isbn13(this.isbn13)
                .cover(this.cover)
                .link(this.link)
                .description(this.description)
                .author(this.author)
                .publisher(this.publisher)
                .publishedDate(this.publishedDate)
                .approved(this.approved)
                .build();
    }
}
