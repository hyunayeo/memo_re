package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Wish;
import kitri.dev6.memore.dto.WishDto;
import kitri.dev6.memore.service.WishService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/wish")
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }
    // 전체 찜 조회
    @GetMapping("/")
    public List<Wish> findAll() {
        return wishService.wishList();
    }
    // 검색 찜 조회
    @GetMapping("/{id}")
    public Wish findById(@PathVariable String id) {
        return wishService.searchWish(Long.parseLong(id));
    }
    // 찜 등록
    @PostMapping("/")
    public void create(@RequestBody WishDto wishdto) {
        wishService.createWish(wishdto);
    }
    // 찜 삭제
    // TODO: use memberId of session(로그인한 멤버id를 쓰게 하기 / 세션 정보)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id, @RequestParam("memberId") String memberId) {
        wishService.deleteWish(Long.parseLong(id), Long.parseLong(memberId));
    }
}
