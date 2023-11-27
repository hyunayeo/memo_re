package kitri.dev6.memore.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Book {
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
    private Boolean approved;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public void update(String title, String isbn, String isbn13,
                       String cover, String link, String description, String author,
                       String publisher, LocalDate publishedDate, Boolean approved) {
        this.title = title;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.cover = cover;
        this.link = link;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.approved = approved;
    }
}