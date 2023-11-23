package kitri.dev6.memore.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Wish {
    private Long id;
    private Long member_id;
    private Long book_id;
    private LocalDateTime created_at;
}
