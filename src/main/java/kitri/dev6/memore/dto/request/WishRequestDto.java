package kitri.dev6.memore.dto.request;

import kitri.dev6.memore.domain.Wish;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class WishRequestDto {
    // 데이터 전송 객체
    @NotEmpty
    private Long memberId;
    @NotEmpty
    private Long bookId;

    public Wish toDomain() {
        return Wish.builder()
                .memberId(memberId)
                .bookId(bookId)
                .build();
    }
}
