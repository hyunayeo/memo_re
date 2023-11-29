package kitri.dev6.memore.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
@Getter
public class QuestionUpdateRequestDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}
