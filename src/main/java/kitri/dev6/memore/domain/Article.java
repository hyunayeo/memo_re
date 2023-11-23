package kitri.dev6.memore.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Articles {
    private Long id;
    private String title;
    private String content;
    private int view_count;
    private boolean done;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private int rating_score;
    private boolean personal;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

}
