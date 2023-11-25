package kitri.dev6.memore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishDTO {
    // 데이터 전송 객체
    private String memberId;
    private String bookId;
}
