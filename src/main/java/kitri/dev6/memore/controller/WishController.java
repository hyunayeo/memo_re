package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Wish;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.WishRequestDto;
import kitri.dev6.memore.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishes")
public class WishController {
    private final WishService wishService;
    // 전체 찜 조회
    @GetMapping("")
    public ResponseEntity<PagingResponse<Wish>> findAll(@ModelAttribute("params") SearchDto params) {
        PagingResponse<Wish> wishes = wishService.findAll(params);
        if (wishes == null) {
            throw new IllegalArgumentException("No wishes");
        }
        return new ResponseEntity<>(wishes, HttpStatus.OK);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<Wish> findByBookId(@PathVariable Long bookId, @RequestParam("memberId") Long memberId) {
        Wish wish = wishService.findByBookId(bookId, memberId);
        if (wish == null) {
            return null;
        }
        return new ResponseEntity<>(wish, HttpStatus.OK);
    }
    // 찜 등록
    @PostMapping("")
    public void insert(@RequestBody WishRequestDto wishRequestDto) {
        wishService.insert(wishRequestDto);
    }
    // 찜 삭제
    // TODO: use memberId of session(로그인한 멤버id를 쓰게 하기 / 세션 정보)
    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable Long bookId, @RequestParam("memberId") Long memberId) {
        wishService.delete(bookId, memberId);
    }
}
