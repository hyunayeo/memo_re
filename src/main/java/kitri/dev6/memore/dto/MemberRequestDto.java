package kitri.dev6.memore.dto;


import kitri.dev6.memore.domain.Member;
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

    public Member toDomain(){
        return Member.builder()
                .email(email)
                .number(number)
                .name(name)
                .password(password)
                .picture(picture)
                .build();
    }

}
