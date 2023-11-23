package kitri.dev6.memore.service;

import kitri.dev6.memore.controller.WishTest;
import kitri.dev6.memore.entity.Member;
import kitri.dev6.memore.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public Member findById(Long id) {
        return memberMapper.findById(id);
    }
}
