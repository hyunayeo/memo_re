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
    public Member findById(@PathVariable String id){
        return memberService.findById(Long.parseLong(id));
    }
    @GetMapping("/")
    public List<Member> findAll(){
        return memberService.findAll();
    }

    @PostMapping("/join")
    public void insert(@RequestBody @Valid final MemberRequest memberRequest){
        memberService.insert(memberRequest);
    }
    @PatchMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody MemberRequest memberRequest){
        memberService.update(Long.parseLong(id), memberRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        memberService.deleteById(Long.parseLong(id));
    }
}
