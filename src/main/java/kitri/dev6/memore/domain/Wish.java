package kitri.dev6.memore.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Wish {
    // 테이블과 일치하게 구조화된 데이터 객체 / Wish목록에서 필요한 데이터
    private Long id;
    private Long memberId;
    private Long bookId;
    private LocalDateTime createdAt;
}
