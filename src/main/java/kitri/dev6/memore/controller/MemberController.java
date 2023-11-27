package kitri.dev6.memore.controller;

import kitri.dev6.memore.dto.MemberRequestDto;
import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.dto.MemberResponseDto;
import kitri.dev6.memore.dto.MemberUpdateRequestDto;
import kitri.dev6.memore.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;
    @GetMapping("/{id}")
    public MemberResponseDto findById(@PathVariable Long id){
        return memberService.findById(id);
    }
    @GetMapping("")
    public List<Member> findAll(){
        return memberService.findAll();
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
    public Long deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        return id;
    }
}
