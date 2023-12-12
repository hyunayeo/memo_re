package kitri.dev6.memore.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wish {
    // 테이블과 일치하게 구조화된 데이터 객체 / Wish 목록에서 필요한 데이터
    private Long id;
    private Long memberId;
    private Long bookId;
    private LocalDateTime createdAt;
    private Book book;
}
