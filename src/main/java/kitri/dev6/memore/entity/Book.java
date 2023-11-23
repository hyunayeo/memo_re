package kitri.dev6.memore.entity;

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
    private Long category_id;
    private Long member_id;
    private String title;
    private String isbn;
    private String isbn13;
    private String cover;
    private String link;
    private String description;
    private String author;
    private String publisher;
    private LocalDate published_date;
    private boolean approved;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;
}