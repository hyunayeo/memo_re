package kitri.dev6.memore.dto.response;

import kitri.dev6.memore.domain.Book;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Builder
public class BookResponseDto {
    private Long id;
    private Long categoryId;
    private Long memberId;
    private String title;
    private String isbn;
    private String isbn13;
    private String cover;
    private String link;
    private String description;
    private String author;
    private String publisher;
    private LocalDate publishedDate;
    private boolean approved;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
//    public BookResponseDto(Book domain){
//        this.id = domain.getId();
//        this.categoryId = domain.getCategoryId();
//        this.memberId = domain.getMemberId();
//        this.title = domain.getTitle();
//        this.isbn = domain.getIsbn();
//        this.isbn13 = domain.getIsbn13();
//        this.cover = domain.getCover();
//        this.link = domain.getLink();
//        this.description = domain.getDescription();
//        this.author = domain.getAuthor();
//        this.publisher = domain.getPublisher();
//        this.publishedDate = domain.getPublishedDate();
//        this.approved = domain.getApproved();
//        this.createdAt = domain.getCreatedAt();
//        this.modifiedAt = domain.getModifiedAt();
//    }

    public static BookResponseDto toDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .categoryId(book.getCategoryId())
                .memberId(book.getMemberId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .isbn13(book.getIsbn13())
                .cover(book.getCover())
                .link(book.getLink())
                .description(book.getDescription())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .approved(book.getApproved())
                .createdAt(book.getCreatedAt())
                .modifiedAt(book.getModifiedAt())
                .build();
    }

}
