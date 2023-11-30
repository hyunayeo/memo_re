package kitri.dev6.memore.dto.response;

import kitri.dev6.memore.domain.Question;
import kitri.dev6.memore.domain.Wish;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
public class WishResponseDto extends RepresentationModel<WishResponseDto> {
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
