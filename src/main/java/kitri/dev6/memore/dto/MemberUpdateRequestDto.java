package kitri.dev6.memore.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
@Getter
@Builder
public class MemberUpdateRequestDto {
    @NotEmpty
    private String number;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private String picture;
}