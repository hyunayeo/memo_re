package kitri.dev6.memore.service;

import kitri.dev6.memore.dto.MemberRequest;
import kitri.dev6.memore.entity.Member;
import kitri.dev6.memore.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public Member findById(Long id) {
        return memberMapper.findById(id);
    }

    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    public void insert(MemberRequest memberRequest) {
        Member member = Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .number(memberRequest.getNumber())
                .password(memberRequest.getPassword())
                .picture(memberRequest.getPicture())
                .build();
        memberMapper.insert(member);
    }

    public void update(Long id, MemberRequest memberRequest) {
        Member member = memberMapper.findById(id);
        member.setName(memberRequest.getName());
        member.setNumber(memberRequest.getNumber());
        member.setPassword(memberRequest.getPassword());
        member.setPicture(memberRequest.getPicture());
        memberMapper.updateById(member);
    }

    public void deleteById(Long id) {
        // TODO: 2023-11-23 관리자만 삭제 가능하게 변경
        memberMapper.deleteById(id);
    }
}
