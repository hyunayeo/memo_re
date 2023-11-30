package kitri.dev6.memore.controller;

import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.MemberRequestDto;
import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.dto.response.ArticleResponseDto;
import kitri.dev6.memore.dto.response.MemberResponseDto;
import kitri.dev6.memore.dto.request.MemberUpdateRequestDto;
import kitri.dev6.memore.service.MemberService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
//    @GetMapping()
//    public PagingResponse<MemberResponseDto> findAll(@ModelAttribute("params") SearchDto params){
//        return memberService.findAll(params);
//    }
    @GetMapping()
    public ResponseEntity<PagingResponse<MemberResponseDto>> findAll(@ModelAttribute("params") SearchDto params){
        PagingResponse<MemberResponseDto> memberResponseDtos = memberService.findAll(params);
        if (memberResponseDtos == null) {
            throw new IllegalArgumentException("No members");
        }

        for (MemberResponseDto memberResponseDto : memberResponseDtos.getList()) {
            Long memberId = memberResponseDto.getId();
            Link selfLink = linkTo(MemberController.class).slash(memberId).withSelfRel();
            memberResponseDto.add(selfLink);
            memberResponseDto.add(linkTo(methodOn(ArticleController.class).findAll(new SearchDto())).slash("?searchType=member_id&searchKeyword=" + memberId).withRel("articles"));
            memberResponseDto.add(linkTo(methodOn(QuestionController.class).findAll(new SearchDto())).slash("?searchType=member_id&searchKeyword=" + memberId).withRel("questions"));
            memberResponseDto.add(linkTo(methodOn(WishController.class).findAll(new SearchDto())).slash("?searchType=member_id&searchKeyword=" + memberId).withRel("wishes"));
            memberResponseDto.add(linkTo(methodOn(BookController.class).findAll(new SearchDto())).slash("?searchType=member_id&searchKeyword=" + memberId).withRel("books"));
        }
        memberResponseDtos.set_links(linkTo(MemberController.class).withSelfRel());

        return new ResponseEntity<>(memberResponseDtos, HttpStatus.OK);
    }
    // "FOR THE HATEOAS TEST"
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findById(id);
        if (memberResponseDto == null) {
            throw new IllegalArgumentException("No member");
        }

        memberResponseDto.add(linkTo(methodOn(MemberController.class).findAll(new SearchDto())).slash(id).withRel("self"));
        memberResponseDto.add(linkTo(methodOn(ArticleController.class).findAll(new SearchDto())).slash("?searchType=member_id&searchKeyword=" + id).withRel("articles"));
        memberResponseDto.add(linkTo(methodOn(QuestionController.class).findAll(new SearchDto())).slash("?searchType=member_id&searchKeyword=" + id).withRel("questions"));
        memberResponseDto.add(linkTo(methodOn(WishController.class).findAll(new SearchDto())).slash("?searchType=member_id&searchKeyword=" + id).withRel("wishes"));
        memberResponseDto.add(linkTo(methodOn(BookController.class).findAll(new SearchDto())).slash("?searchType=member_id&searchKeyword=" + id).withRel("books"));

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PostMapping("")
    public Long insert(@RequestBody @Valid final MemberRequestDto memberRequestDto){
        return memberService.insert(memberRequestDto);
    }
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto){
        return memberService.update(id, requestDto);
    }
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        memberService.deleteById(id);
        return id;
    }
}
