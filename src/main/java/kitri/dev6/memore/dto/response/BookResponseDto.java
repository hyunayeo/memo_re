package kitri.dev6.memore.dto.response;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.open_api.AladinBookVO;
import kitri.dev6.memore.dto.open_api.NaverBookVO;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Getter;

@Getter
@NoArgsConstructor
public class BookResponseDto extends RepresentationModel<BookResponseDto> {
    private Long id;
    private Long categoryId;
    private Long memberId;
    private String title;
    private String isbn;
    private String cover;
    private String link;
    private String description;
    private String author;
    private String publisher;
    private LocalDate publishedDate;
    private boolean approved;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private List<ArticleResponseDto> articles;

    public BookResponseDto(Book domain){
        this.id = domain.getId();
        this.categoryId = domain.getCategoryId();
        this.memberId = domain.getMemberId();
        this.title = domain.getTitle();
        this.isbn = domain.getIsbn();
        this.cover = domain.getCover();
        this.link = domain.getLink();
        this.description = domain.getDescription();
        this.author = domain.getAuthor();
        this.publisher = domain.getPublisher();
        this.publishedDate = domain.getPublishedDate();
        this.approved = domain.getApproved();
        this.createdAt = domain.getCreatedAt();
        this.modifiedAt = domain.getModifiedAt();
    }

    public BookResponseDto(NaverBookVO book){
        this.categoryId = 1L;
        this.memberId = 1L;
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.cover = book.getImage();
        this.link = book.getLink();
        this.description = book.getDescription();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.publishedDate = LocalDate.parse(book.getPubdate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public BookResponseDto(AladinBookVO book){
        this.categoryId = 1L;
        this.memberId = 1L;
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.cover = book.getCover();
        this.link = book.getLink();
        this.description = book.getDescription();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.publishedDate = LocalDate.parse(book.getPubDate());
    }

}
