package kitri.dev6.memore.dto.response;

import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.domain.Question;

import java.time.LocalDateTime;

public class QuestionResponseDto {
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    public QuestionResponseDto(Question domain){
        this.id = domain.getId();
        this.memberId = domain.getMemberId();
        this.title = domain.getTitle();
        this.content = domain.getContent();
        this.createAt = domain.getCreateAt();
        this.modifiedAt = domain.getModifiedAt();
    }
}
