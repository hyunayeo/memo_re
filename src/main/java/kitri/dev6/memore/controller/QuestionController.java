package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Question;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.QuestionRequestDto;
import kitri.dev6.memore.dto.request.QuestionUpdateRequestDto;
import kitri.dev6.memore.dto.response.QuestionResponseDto;
import kitri.dev6.memore.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("")
    public PagingResponse<QuestionResponseDto> findAll(@ModelAttribute("params") SearchDto params){
        return questionService.findAll(params);
    }
    @GetMapping("/{id}")
    public Question findById(@PathVariable Long id) {
        return questionService.findById(id);
    }
    @PostMapping("")
    public Long insert(@RequestBody QuestionRequestDto requestDto) {
        return questionService.insert(requestDto);
    }
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid final QuestionUpdateRequestDto requestDto) {
        return questionService.updateById(id, requestDto);
    }
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        questionService.delete(id);
        return id;
    }
}
