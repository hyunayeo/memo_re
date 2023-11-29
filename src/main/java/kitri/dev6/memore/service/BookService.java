package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.request.BookRequestDto;
import kitri.dev6.memore.dto.response.BookResponseDto;
import kitri.dev6.memore.dto.request.BookUpdateRequestDto;
import kitri.dev6.memore.repository.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;

    public BookResponseDto findById(Long id) {
        Book book = bookMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다, id=" + id));
        return BookResponseDto.toDto(book);
    }

    public List<Book> findAll(Long memberId) {
        if (memberId == null) {
            return bookMapper.findAll();
        }
        return bookMapper.findAllByMemberId(memberId);
    }

    public Long insert(BookRequestDto bookRequestDto) {
        Book book = bookRequestDto.toDomain();

        bookMapper.insert(book);
        return book.getId();
    }

    public Long update(Long id, BookUpdateRequestDto bookRequestDto) {
        Book book = bookMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));
        book.update(
                bookRequestDto.getTitle(),
                bookRequestDto.getIsbn(),
                bookRequestDto.getIsbn13(),
                bookRequestDto.getCover(),
                bookRequestDto.getLink(),
                bookRequestDto.getDescription(),
                bookRequestDto.getAuthor(),
                bookRequestDto.getPublisher(),
                bookRequestDto.getPublishedDate(),
                bookRequestDto.getApproved()
        );
        bookMapper.updateById(book);
        return id;
    }

    public void delete(Long id) {
        bookMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));
        bookMapper.deleteById(id);
    }
}
