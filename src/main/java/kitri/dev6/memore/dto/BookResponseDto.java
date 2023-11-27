package kitri.dev6.memore.dto;

import kitri.dev6.memore.domain.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    public BookResponseDto(Book domain){
        this.id = domain.getId();
        this.categoryId = domain.getCategoryId();
        this.memberId = domain.getMemberId();
        this.title = domain.getTitle();
        this.isbn = domain.getIsbn();
        this.isbn13 = domain.getIsbn13();
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
}
