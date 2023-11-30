package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.ArticleRequestDto;
import kitri.dev6.memore.dto.response.ArticleResponseDto;
import kitri.dev6.memore.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
//    @GetMapping(params = {"member_id"})
//    public PagingResponse<ArticleResponseDto> findByMemberID(@ModelAttribute("params") SearchDto params, @RequestParam("member_id") String memberId) {
//        return articleService.findByMemberId(params, Long.parseLong(memberId));
//    }
//    @GetMapping(params = {"book_id"})
//    public PagingResponse<ArticleResponseDto> findByBookId(@ModelAttribute("params") SearchDto params, @RequestParam("book_id") String bookId) {
//        return articleService.findByBookId(params, Long.parseLong(bookId));
//    }
    @GetMapping
    public PagingResponse<ArticleResponseDto> findAll(@ModelAttribute("params") SearchDto params) {
        return articleService.findAll(params);
    }
    @GetMapping("/{id}")
    public ArticleResponseDto findById(@PathVariable Long id) {
        return articleService.findById(id);
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
