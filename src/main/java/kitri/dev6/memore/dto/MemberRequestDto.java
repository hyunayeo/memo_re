package kitri.dev6.memore.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
@Setter
@Getter
@Builder
public class MemberRequestDto {
    @NotEmpty
    private String email;
    @NotEmpty
    private String number;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private String picture;
}
