package kitri.dev6.memore.controller;

import kitri.dev6.memore.dto.MemberRequest;
import kitri.dev6.memore.entity.Member;
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
    public Member findById(@PathVariable Long id){
        return memberService.findById(id);
    }
    @GetMapping("/")
    public List<Member> findAll(){
        return memberService.findAll();
    }

    @PostMapping("/join")
    public Long insert(@RequestBody @Valid final MemberRequest memberRequest){
        return memberService.insert(memberRequest);
    }
    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemberRequest memberRequest){
        return memberService.update(id, memberRequest);
    }
    @GetMapping("/delete/{id}")
    public Long deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        return id;
    }
}
