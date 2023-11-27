package kitri.dev6.memore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishDto {
    // 데이터 전송 객체
    private Long memberId;
    private Long bookId;
}
