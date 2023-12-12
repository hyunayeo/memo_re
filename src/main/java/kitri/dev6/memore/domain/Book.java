package kitri.dev6.memore.domain;

import kitri.dev6.memore.dto.open_api.AladinBookVO;
import kitri.dev6.memore.dto.open_api.NaverBookVO;
import kitri.dev6.memore.dto.response.ArticleResponseDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
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
    private Boolean approved;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private List<ArticleResponseDto> articles;

    public void update(String title, String isbn,
                       String cover, String link, String description, String author,
                       String publisher, LocalDate publishedDate, Boolean approved) {
        this.title = title;
        this.isbn = isbn;
        this.cover = cover;
        this.link = link;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.approved = approved;
    }

    public Book(NaverBookVO book){
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

    public Book(AladinBookVO book){
        this.categoryId = book.getCategoryId();
        this.memberId = 1L;
        this.title = book.getTitle();
//        this.isbn = book.getIsbn();
        this.isbn = book.getIsbn();
        this.cover = book.getCover();
        this.link = book.getLink();
        this.description = book.getDescription();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.publishedDate = LocalDate.parse(book.getPubDate());
    }
}