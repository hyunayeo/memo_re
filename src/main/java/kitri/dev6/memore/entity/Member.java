package kitri.dev6.memore.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Member {
    Long id;
    String email;
    String number;
    String name;
    String password;
    String picture;
    LocalDateTime createAt;
    LocalDateTime modifiedAt;
    LocalDateTime deletedAt;
}
