package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Question;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.QuestionRequestDto;
import kitri.dev6.memore.dto.request.QuestionUpdateRequestDto;
import kitri.dev6.memore.dto.response.QuestionResponseDto;
import kitri.dev6.memore.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("")
    public ResponseEntity<PagingResponse<Question>> findAll(@ModelAttribute("params") SearchDto params){
        PagingResponse<Question> questions = questionService.findAll(params);
        if (questions == null) {
            throw new IllegalArgumentException("No questions");
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> findById(@PathVariable Long id) {
        Question question = questionService.findById(id);
        if (question == null) {
            throw new IllegalArgumentException("No question");
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
    @PostMapping("")
    public Long insert(@RequestBody QuestionRequestDto requestDto) {
        return questionService.insert(requestDto);
    }
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid final QuestionUpdateRequestDto requestDto) {
        return questionService.update(id, requestDto);
    }
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        questionService.delete(id);
        return id;
    }
}
