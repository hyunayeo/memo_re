package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Question;
import kitri.dev6.memore.dto.QuestionRequestDto;
import kitri.dev6.memore.dto.QuestionUpdateRequestDto;
import kitri.dev6.memore.repository.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionMapper questionMapper;

    public List<Question> findAll(Long id) {
        if (id==null){
            return questionMapper.findAll();
        }
        return questionMapper.findByMemberId(id);
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
