package kitri.dev6.memore.controller;

import kitri.dev6.memore.entity.Member;
import kitri.dev6.memore.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;
    @GetMapping("/{id}")
    public Member findById(@PathVariable String id){
        return memberService.findById(Long.parseLong(id));
    }
}
