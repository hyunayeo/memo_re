package kitri.dev6.memore.dto.response;

import java.time.LocalDateTime;

public class QuestionResponseDto {
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
