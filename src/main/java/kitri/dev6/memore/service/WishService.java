package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Wish;
import kitri.dev6.memore.dto.WishRequestDto;
import kitri.dev6.memore.repository.WishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishMapper wishMapper;

    // 전체 찜 조회
    public List<Wish> findAll(Long memberId) {
        if (memberId == null) {
            return wishMapper.findAll();
        }
        return wishMapper.findByMemberId(memberId);
    }
    // 찜 등록 : 회원의 찜 목록에 book정보를 담아야 한다.
    public void createWish(WishRequestDto wishRequestDto) {
        wishMapper.create(wishRequestDto.toDomain());
    }

    // 찜 삭제 : 찜 목록에 담긴 id를 회원의 찜 목록에서 삭제해야 한다.
    public void deleteById(Long id, Long memberId) {
        wishMapper.delete(id, memberId);
    }
}
