package kitri.dev6.memore.dto.response;

import kitri.dev6.memore.domain.Question;
import kitri.dev6.memore.domain.Wish;

import java.time.LocalDateTime;

public class WishResponseDto {
    private Long id;
    private Long memberId;
    private Long bookId;
    private LocalDateTime createdAt;
    public WishResponseDto(Wish domain){
        this.id = domain.getId();
        this.memberId = domain.getMemberId();
        this.bookId = domain.getBookId();
        this.createdAt = domain.getCreatedAt();
    }
}
