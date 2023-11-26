package kitri.dev6.memore.service;

import com.fasterxml.classmate.MemberResolver;
import kitri.dev6.memore.dto.MemberRequestDto;
import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.dto.MemberResponseDto;
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
    public MemberResponseDto findById(Long id) {
        Member member = memberMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원 정보가 없습니다."));
        return new MemberResponseDto(member);
    }

    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Transactional
    public Long insert(MemberRequestDto memberRequestDto) {
        Member member = memberRequestDto.toDomain();
        memberMapper.insert(member);
        return member.getId();
    }

    @Transactional
    public Long update(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다"));
        member.update(memberRequestDto.getNumber(),
                memberRequestDto.getName(),
                memberRequestDto.getPassword(),
                memberRequestDto.getPicture());
        memberMapper.updateById(member);
        return id;
    }

    public void deleteById(Long id) {
        // TODO: 2023-11-23 관리자만 삭제 가능하게 변경
        memberMapper.deleteById(id);
    }
}