package kitri.dev6.memore.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Member {
    private Long id;
    private String email;
    private String number;
    private String name;
    private String password;
    private String picture;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
}
