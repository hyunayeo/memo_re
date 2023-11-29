package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.request.BookRequestDto;
import kitri.dev6.memore.dto.response.BookResponseDto;
import kitri.dev6.memore.dto.request.BookUpdateRequestDto;
import kitri.dev6.memore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("")
    public List<Book> findAll(@RequestParam(name = "memberId", required = false) Long memberId){
        return bookService.findAll(memberId);
    }
    @GetMapping("/{id}")
    public BookResponseDto findById(@PathVariable Long id){
        return bookService.findById(id);
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
