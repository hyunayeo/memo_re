package kitri.dev6.memore.domain;

import lombok.*;

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
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

    private List<Article> articles;
    private List<Wish> wishes;
    private List<Question> questions;

    public void update(String number, String name, String password, String picture) {
        this.number = number;
        this.name = name;
        this.password = password;
        this.picture = picture;
    }
}
