package kitri.dev6.memore.controller;

import kitri.dev6.memore.dto.BookRequest;
import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("/")
    public List<Book> findAll(){
        return bookService.findAll();
    }
    @GetMapping("/{id}")
    public Book findById(@PathVariable String id){
        return bookService.findById(Long.parseLong(id));
    }
    @PostMapping("/create")
    public void create(@RequestBody BookRequest bookRequest){
        bookService.create(bookRequest);
    }

}
