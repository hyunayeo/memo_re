package kitri.dev6.memore.controller;

import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.QuestionRequestDto;
import kitri.dev6.memore.dto.request.QuestionUpdateRequestDto;
import kitri.dev6.memore.dto.response.BookResponseDto;
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
    public ResponseEntity<PagingResponse<QuestionResponseDto>> findAll(@ModelAttribute("params") SearchDto params){
        PagingResponse<QuestionResponseDto> questionResponseDtos = questionService.findAll(params);
        if (questionResponseDtos == null) {
            throw new IllegalArgumentException("No questions");
        }

        for (QuestionResponseDto questionResponseDto : questionResponseDtos.getList()) {
            Long questionId = questionResponseDto.getId();
            Link selfLink = linkTo(QuestionController.class).slash(questionId).withSelfRel();
            questionResponseDto.add(selfLink);
        }
        questionResponseDtos.set_links(linkTo(QuestionController.class).withSelfRel());
        return new ResponseEntity<>(questionResponseDtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> findById(@PathVariable Long id) {
        QuestionResponseDto questionResponseDto = questionService.findById(id);
        if (questionResponseDto == null) {
            throw new IllegalArgumentException("No question");
        }
        questionResponseDto.add(linkTo(methodOn(QuestionController.class).findAll(new SearchDto())).slash(id).withRel("self"));

        return new ResponseEntity<>(questionResponseDto, HttpStatus.OK);
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
