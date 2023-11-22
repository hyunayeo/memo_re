package kitri.dev6.memore.controller;

import kitri.dev6.memore.entity.Member;
import kitri.dev6.memore.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    MemberMapper memberMapper;
    @GetMapping("/{id}")
    public Member findById(@PathVariable String id) {
        return memberMapper.findById(Long.parseLong(id));
    }
}
