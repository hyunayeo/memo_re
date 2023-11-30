package kitri.dev6.memore.controller;

import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.WishRequestDto;
import kitri.dev6.memore.dto.response.WishResponseDto;
import kitri.dev6.memore.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishes")
public class WishController {
    private final WishService wishService;
    // 전체 찜 조회
    @GetMapping("")
    public ResponseEntity<PagingResponse<WishResponseDto>> findAll(@ModelAttribute("params") SearchDto params) {
        PagingResponse<WishResponseDto> wishResponseDtos = wishService.findAll(params);
        if (wishResponseDtos == null) {
            throw new IllegalArgumentException("No wishes");
        }

        for (WishResponseDto wishResponseDto : wishResponseDtos.getList()) {
            Long wishId = wishResponseDto.getId();
            Link selfLink = linkTo(WishController.class).slash(wishId).withSelfRel();
            wishResponseDto.add(selfLink);
        }
        wishResponseDtos.set_links(linkTo(WishController.class).withSelfRel());
        return new ResponseEntity<>(wishResponseDtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<WishResponseDto> findById(@PathVariable Long id) {
        WishResponseDto wishResponseDto = wishService.findById(id);
        if (wishResponseDto == null) {
            throw new IllegalArgumentException("No wish");
        }
        wishResponseDto.add(linkTo(methodOn(WishController.class).findAll(new SearchDto())).slash(id).withRel("self"));

        return new ResponseEntity<>(wishResponseDto, HttpStatus.OK);
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
