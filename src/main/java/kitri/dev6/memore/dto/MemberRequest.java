package kitri.dev6.memore.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
@Getter
@NoArgsConstructor
public class MemberRequest {
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
