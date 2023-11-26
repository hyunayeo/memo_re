package kitri.dev6.memore.service;

import kitri.dev6.memore.dto.BookRequestDto;
import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.BookResponseDto;
import kitri.dev6.memore.repository.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;

    public BookResponseDto findById(Long id) {
        Book book = bookMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다, id=" + id));
        return new BookResponseDto(book);
    }

    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    public Long insert(BookRequestDto bookRequestDto) {
        Book book = bookRequestDto.toDomain();
        bookMapper.insert(book);
        return book.getId();
    }

    public Long update(Long id, BookRequestDto bookRequestDto){
        Book book = bookMapper.findById(id).orElseThrow(()->new IllegalArgumentException("해당 도서가 존재하지 않습니다."));
        book.update(bookRequestDto.getCategoryId(),
                bookRequestDto.getMemberId(),
                bookRequestDto.getTitle(),
                bookRequestDto.getIsbn(),
                bookRequestDto.getIsbn13(),
                bookRequestDto.getCover(),
                bookRequestDto.getLink(),
                bookRequestDto.getDescription(),
                bookRequestDto.getAuthor(),
                bookRequestDto.getPublisher(),
                bookRequestDto.getPublishedDate(),
                bookRequestDto.isApproved()
        );
        bookMapper.updateById(book);
        return id;
    }
}
