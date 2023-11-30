package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.ArticleRequestDto;
import kitri.dev6.memore.dto.response.ArticleResponseDto;
import kitri.dev6.memore.dto.response.MemberResponseDto;
import kitri.dev6.memore.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    @GetMapping
    public ResponseEntity<PagingResponse<ArticleResponseDto>> findAll(@ModelAttribute("params") SearchDto params) {
        PagingResponse<ArticleResponseDto> articleResponseDtos = articleService.findAll(params);
        if (articleResponseDtos == null) {
            throw new IllegalArgumentException("No Articles");
        }

        for (ArticleResponseDto articleResponseDto : articleResponseDtos.getList()) {
            Long articleId = articleResponseDto.getId();
            Link selfLink = linkTo(ArticleController.class).slash(articleId).withSelfRel();
            articleResponseDto.add(selfLink);
        }
        articleResponseDtos.set_links(linkTo(ArticleController.class).withSelfRel());

        return new ResponseEntity<>(articleResponseDtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> findById(@PathVariable Long id) {
        ArticleResponseDto articleResponseDto = articleService.findById(id);
        if (articleResponseDto == null) {
            throw new IllegalArgumentException("No article");
        }
        articleResponseDto.add(linkTo(methodOn(ArticleController.class).findAll(new SearchDto())).slash(id).withRel("self"));

        return new ResponseEntity<>(articleResponseDto, HttpStatus.OK);
    }
    @PostMapping
    public Long insert(@RequestBody ArticleRequestDto articleRequestDto) {
        return articleService.insert(articleRequestDto);
    }
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArticleRequestDto articleRequestDto) {
        articleService.update(id, articleRequestDto);
        return id;
    }
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        articleService.delete(id);
        return id;
    }
}
