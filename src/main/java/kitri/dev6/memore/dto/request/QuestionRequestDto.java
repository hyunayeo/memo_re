package kitri.dev6.memore.dto.request;

import kitri.dev6.memore.domain.Question;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
@Getter
public class QuestionRequestDto {
    @NotEmpty
    private Long memberId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public Question toDomain(){
        return Question.builder()
                .memberId(memberId)
                .title(title)
                .content(content)
                .build();
    }
}
