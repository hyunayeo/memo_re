package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.common.Converter;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.BookRequestDto;
import kitri.dev6.memore.dto.response.BookResponseDto;
import kitri.dev6.memore.dto.request.BookUpdateRequestDto;
import kitri.dev6.memore.repository.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;
    private final BookOpenApiService bookOpenApiService;

    public BookResponseDto findById(Long id) {
        Book book = bookMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다, id=" + id));
        return new BookResponseDto(book);
    }

    public BookResponseDto findByIsbn(Long isbn) {
        Optional<Book> book = null;

        book = bookMapper.findByIsbn(isbn);
        if (book.isEmpty()) {
            // 알라딘 API에서 가져오기 by isbn
            Long id = bookMapper.insert(bookOpenApiService.fetchOneFromAladin(isbn));
            if (id == null) {
                new IllegalArgumentException("DB에 저장되지 않았습니다.");
            }
            book = bookMapper.findByIsbn(isbn);
        }
        return new BookResponseDto(book.get());
    }

    public PagingResponse<BookResponseDto> findAll(SearchDto params) {
        params.setDomainType("book");
        List<? extends Object> list = null;
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        if (params.shouldIUseNaverBookApi()) {
            list = bookOpenApiService.fetchAllFromNaver(params);
        } else if (params.shouldIUseAladinBookApi()) {
            list = bookOpenApiService.fetchAllFromAladin(params);
        } else {
            list = findAllFromDB(params);
        }

        List<BookResponseDto> convertedList = (List<BookResponseDto>) (Object) Converter.toDto(list);

        return new PagingResponse<>(convertedList, params.getPagination());
    }

    public List<Book> findAllFromDB(SearchDto params) {
        params.setPaging(bookMapper.count(params));
        return bookMapper.findAll(params);
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
