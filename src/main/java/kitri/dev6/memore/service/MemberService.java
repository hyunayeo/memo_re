package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.common.Converter;
import kitri.dev6.memore.dto.common.Pagination;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.MemberRequestDto;
import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.dto.response.ArticleResponseDto;
import kitri.dev6.memore.dto.response.BookResponseDto;
import kitri.dev6.memore.dto.response.MemberResponseDto;
import kitri.dev6.memore.dto.request.MemberUpdateRequestDto;
import kitri.dev6.memore.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public PagingResponse<MemberResponseDto> findAll(SearchDto params) {
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        params.setDomainType("member");
        int count = memberMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<Member> list = memberMapper.findAll(params);
        // domain -> dto
        List<MemberResponseDto> convertedList = (List<MemberResponseDto>) (Object) Converter.domainListTodtoList(list);

        return new PagingResponse<>(convertedList, pagination);
    }

    @Transactional
    public MemberResponseDto findById(Long id) {
        Member member = memberMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원 정보가 없습니다."));
        return new MemberResponseDto(member);
    }

    @Transactional
    public Long insert(MemberRequestDto memberRequestDto) {
        Member member = memberRequestDto.toDomain();
        memberMapper.insert(member);
        return member.getId();
    }

    @Transactional
    public Long update(Long id, MemberUpdateRequestDto requestDto) {
        Member member = memberMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다"));
        member.update(requestDto.getNumber(),
                requestDto.getName(),
                requestDto.getPassword(),
                requestDto.getPicture());
        memberMapper.updateById(member);
        return id;
    }

    public void deleteById(Long id) {
        // TODO: 2023-11-23 관리자만 삭제 가능하게 변경
        memberMapper.deleteById(id);
    }
}
