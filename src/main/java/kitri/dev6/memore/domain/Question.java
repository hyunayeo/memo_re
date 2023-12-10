package kitri.dev6.memore.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Question {
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private Boolean complete;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
