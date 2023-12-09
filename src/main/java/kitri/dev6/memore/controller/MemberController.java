package kitri.dev6.memore.controller;

import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.MemberRequestDto;
import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.dto.request.MemberUpdateRequestDto;
import kitri.dev6.memore.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping()
    public ResponseEntity<PagingResponse<Member>> findAll(@ModelAttribute("params") SearchDto params){
        PagingResponse<Member> members = memberService.findAll(params);
        if (members == null) {
            throw new IllegalArgumentException("No members");
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> findById(@PathVariable Long id) {
        Member member = memberService.findById(id);
        if (member == null) {
            throw new IllegalArgumentException("No member");
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
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
        memberService.delete(id);
        return id;
    }
}
