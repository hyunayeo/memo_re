package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Wish;
import kitri.dev6.memore.dto.WishRequestDto;
import kitri.dev6.memore.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/wishes")
public class WishController {
    private final WishService wishService;

    // 전체 찜 조회
    @GetMapping("")
    public List<Wish> findAll(@RequestParam(value = "memberId",required = false) Long memberId) {
        return wishService.findAll(memberId);
    }

    // 찜 등록
    @PostMapping("")
    public void create(@RequestBody WishRequestDto wishRequestDto) {
        wishService.createWish(wishRequestDto);
    }

    // 찜 삭제
    // TODO: use memberId of session(로그인한 멤버id를 쓰게 하기 / 세션 정보)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @RequestParam("memberId") Long memberId) {
        wishService.deleteById(id, memberId);
    }
}
