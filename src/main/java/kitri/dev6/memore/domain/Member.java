package kitri.dev6.memore.domain;

import kitri.dev6.memore.domain.user.Role;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String email;
    private String number;
    private String name;
    private String password;
    private String picture;
    private Role role;
    private String social;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

    private List<Article> articles;
    private List<Wish> wishes;
    private List<Question> questions;

    public Member update(String number, String name, String password, String picture, String social) {
        this.number = number;
        this.name = name;
        this.password = password;
        this.picture = picture;
        this.social = social;
        return this;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }
}
