package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.BookRequestDto;
import kitri.dev6.memore.dto.request.BookUpdateRequestDto;
import kitri.dev6.memore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PagingResponse<Book>> findAll(@ModelAttribute("params") SearchDto params){
        PagingResponse<Book> books = bookService.findAll(params);
        if (books == null) {
            throw new IllegalArgumentException("No members");
        }

//        for (Book book : books.getList()) {
//            String isbn = book.getIsbn13();
//            Link selfLink = linkTo(BookController.class).slash("?searchType=book_id&searchKeyword=" + isbn).withSelfRel();
//            book.add(selfLink);
//            book.add(linkTo(methodOn(ArticleController.class).findAll(new SearchDto())).slash("?searchType=book_id&searchKeyword=" + isbn).withRel("articles"));
//            book.add(linkTo(methodOn(WishController.class).findAll(new SearchDto())).slash("?searchType=book_id&searchKeyword=" + isbn).withRel("wishes"));
//        }
//        books.set_links(linkTo(BookController.class).withSelfRel());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable String id, @ModelAttribute("params") SearchDto params){
        Book book = null;
        if (params.getSearchType().equals("isbn")) {
            book = bookService.findByIsbn(id);
        } else {
            book = bookService.findById(Long.parseLong(id));
        }
        if (book == null) {
            throw new IllegalArgumentException("No book");
        }
//        book.add(linkTo(methodOn(BookController.class).findAll(new SearchDto())).slash(id).withRel("self"));
//        book.add(linkTo(methodOn(ArticleController.class).findAll(new SearchDto())).slash("?searchType=book_id&searchKeyword=" + id).withRel("articles"));
//        book.add(linkTo(methodOn(WishController.class).findAll(new SearchDto())).slash("?searchType=book_id&searchKeyword=" + id).withRel("wishes"));

        return new ResponseEntity<>(book, HttpStatus.OK);
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
