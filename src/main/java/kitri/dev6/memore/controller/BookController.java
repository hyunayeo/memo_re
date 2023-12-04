package kitri.dev6.memore.controller;

import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.BookRequestDto;
import kitri.dev6.memore.dto.response.BookResponseDto;
import kitri.dev6.memore.dto.request.BookUpdateRequestDto;
import kitri.dev6.memore.service.BookService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("")
    public ResponseEntity<PagingResponse<BookResponseDto>> findAll(@ModelAttribute("params") SearchDto params){
        PagingResponse<BookResponseDto> bookResponseDtos = bookService.findAll(params);
        if (bookResponseDtos == null) {
            throw new IllegalArgumentException("No members");
        }

        for (BookResponseDto bookResponseDto : bookResponseDtos.getList()) {
            String isbn = bookResponseDto.getIsbn13();
            Link selfLink = linkTo(BookController.class).slash("?searchType=book_id&searchKeyword=" + isbn).withSelfRel();
            bookResponseDto.add(selfLink);
            bookResponseDto.add(linkTo(methodOn(ArticleController.class).findAll(new SearchDto())).slash("?searchType=book_id&searchKeyword=" + isbn).withRel("articles"));
            bookResponseDto.add(linkTo(methodOn(WishController.class).findAll(new SearchDto())).slash("?searchType=book_id&searchKeyword=" + isbn).withRel("wishes"));
        }
        bookResponseDtos.set_links(linkTo(BookController.class).withSelfRel());
        return new ResponseEntity<>(bookResponseDtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable Long id, @ModelAttribute("params") SearchDto params){
        BookResponseDto bookResponseDto = null;
        if (params.getSearchType().equals("isbn")) {
            bookResponseDto = bookService.findByIsbn(id);
        } else {
            bookResponseDto = bookService.findById(id);
        }
        if (bookResponseDto == null) {
            throw new IllegalArgumentException("No book");
        }
        bookResponseDto.add(linkTo(methodOn(BookController.class).findAll(new SearchDto())).slash(id).withRel("self"));
        bookResponseDto.add(linkTo(methodOn(ArticleController.class).findAll(new SearchDto())).slash("?searchType=book_id&searchKeyword=" + id).withRel("articles"));
        bookResponseDto.add(linkTo(methodOn(WishController.class).findAll(new SearchDto())).slash("?searchType=book_id&searchKeyword=" + id).withRel("wishes"));

        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
    @PostMapping("")
    public Long insert(@RequestBody BookRequestDto bookRequestDto){
        return bookService.insert(bookRequestDto);
    }
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid final BookUpdateRequestDto bookRequestDto){
        return bookService.update(id, bookRequestDto);
    }
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        bookService.delete(id);
        return id;
    }

}
