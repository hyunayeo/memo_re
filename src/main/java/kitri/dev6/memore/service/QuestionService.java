package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.domain.Question;
import kitri.dev6.memore.dto.common.Converter;
import kitri.dev6.memore.dto.common.Pagination;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.QuestionRequestDto;
import kitri.dev6.memore.dto.request.QuestionUpdateRequestDto;
import kitri.dev6.memore.dto.response.MemberResponseDto;
import kitri.dev6.memore.dto.response.QuestionResponseDto;
import kitri.dev6.memore.repository.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionMapper questionMapper;

    public PagingResponse<QuestionResponseDto> findAll(SearchDto params) {
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = questionMapper.count();
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<Question> list = questionMapper.findAll(params);
        // domain -> dto
        List<QuestionResponseDto> convertedList = (List<QuestionResponseDto>) (Object) Converter.domainListTodtoList(list);

        return new PagingResponse<>(convertedList, pagination);
    }

    public Question findById(Long id) {
        Question Question = questionMapper.findById(id).orElseThrow(() ->
                new IllegalArgumentException("질문이 존재하지 않습니다. id=" + id));
        return Question;
    }

    public void delete(Long id) {
        this.findById(id);
        questionMapper.delete(id);
    }

    public Long insert(QuestionRequestDto requestDto) {
        Question Question = requestDto.toDomain();
        questionMapper.insert(Question);
        return Question.getId();
    }

    public Long updateById(Long id, QuestionUpdateRequestDto requestDto){
        Question Question = this.findById(id);
        Question.setTitle(requestDto.getTitle());
        Question.setContent(requestDto.getContent());
        questionMapper.updateById(Question);
        return id;
    }
}
