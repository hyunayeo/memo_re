package kitri.dev6.memore.service;

import kitri.dev6.memore.dto.MemberRequestDto;
import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    @Transactional
    public Member findById(Long id) {
        return memberMapper.findById(id);
    }
    public List<Member> findAll() {
        return memberMapper.findAll();
    }
    @Transactional
    public Long insert(MemberRequestDto memberRequestDto) {
        Member member = Member.builder()
                .name(memberRequestDto.getName())
                .email(memberRequestDto.getEmail())
                .number(memberRequestDto.getNumber())
                .password(memberRequestDto.getPassword())
                .picture(memberRequestDto.getPicture())
                .build();
        memberMapper.insert(member);
        return member.getId();
    }
    @Transactional
    public Long update(Long id, MemberRequestDto memberRequestDto) {

        Member member = memberMapper.findById(id);
        member.setName(memberRequestDto.getName());
        member.setNumber(memberRequestDto.getNumber());
        member.setPassword(memberRequestDto.getPassword());
        member.setPicture(memberRequestDto.getPicture());
        return memberMapper.updateById(member);
    }

    public void deleteById(Long id) {
        // TODO: 2023-11-23 관리자만 삭제 가능하게 변경
        memberMapper.deleteById(id);
    }
}
