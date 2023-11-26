package kitri.dev6.memore.dto;

import kitri.dev6.memore.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponseDto {
    private Long id;
    private String email;
    private String number;
    private String name;
    private String password;
    private String picture;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    public MemberResponseDto(Member domain){
        this.id = domain.getId();
        this.email = domain.getEmail();
        this.number = domain.getNumber();
        this.name = domain.getName();
        this.password = domain.getPassword();
        this.picture = domain.getPicture();
        this.createdAt = domain.getCreatedAt();
        this.modifiedAt = domain.getModifiedAt();
        this.deletedAt = domain.getDeletedAt();
    }
}
