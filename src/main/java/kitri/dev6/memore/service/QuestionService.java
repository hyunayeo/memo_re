package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Question;
import kitri.dev6.memore.dto.common.Pagination;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.QuestionRequestDto;
import kitri.dev6.memore.dto.request.QuestionUpdateRequestDto;
import kitri.dev6.memore.repository.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionMapper questionMapper;

    public PagingResponse<Question> findAll(SearchDto params) {

        params.setDomainType("question");
        int count = questionMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<Question> list = questionMapper.findAll(params);

        return new PagingResponse<>(list, pagination);
    }

    public Question findById(Long id) {
        return questionMapper.findById(id);
    }

    public Long insert(QuestionRequestDto requestDto) {
        Question Question = requestDto.toDomain();
        questionMapper.insert(Question);
        return Question.getId();
    }

    public Long update(Long id, QuestionUpdateRequestDto requestDto){
        Question question = questionMapper.findById(id);
        question.setTitle(requestDto.getTitle());
        question.setContent(requestDto.getContent());
        questionMapper.update(question);
        return id;
    }
    public void delete(Long id) {
        questionMapper.delete(id);
    }
}
