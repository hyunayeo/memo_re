package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Wish;
import kitri.dev6.memore.dto.WishDTO;
import kitri.dev6.memore.repository.WishMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private final WishMapper wishMapper;

    // 생성자
    public WishService(WishMapper wishMapper) {
        this.wishMapper = wishMapper;
    }

    // 전체 찜 조회
    public List<Wish> wishList() {
        return wishMapper.findAll();
    }

    // 검색 찜 조회
    public Wish searchWish(Long id) {
        return wishMapper.findById(id);
    }

    // 찜 등록 : 회원의 찜 목록에 book정보를 담아야 한다.
    public void createWish(WishDTO wishDTO) {
        wishMapper.create(wishDTO.getMemberId(), wishDTO.getBookId());
    }

    // 찜 삭제 : 찜 목록에 담긴 id를 회원의 찜 목록에서 삭제해야 한다.
    public void deleteWish(Long id, Long memberId) {
        wishMapper.delete(id, memberId);
    }
}
