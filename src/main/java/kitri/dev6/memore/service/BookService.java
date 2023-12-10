package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.common.Converter;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.BookRequestDto;
import kitri.dev6.memore.dto.request.BookUpdateRequestDto;
import kitri.dev6.memore.repository.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookMapper bookMapper;
    private final BookOpenApiService bookOpenApiService;

    public Book findById(Long id) {
        return bookMapper.findWithArticlesById(id);
    }

    public Book findByIsbn(Long isbn) {
        Book book = null;

        book = bookMapper.findWithArticlesByIsbn(isbn);
        if (book == null) {
            // 알라딘 API에서 가져오기 by isbn
            Long id = bookMapper.insert(bookOpenApiService.fetchOneFromAladin(isbn));
            if (id == null) {
                new IllegalArgumentException("DB에 저장되지 않았습니다.");
            }
            book = bookMapper.findWithArticlesByIsbn(isbn);
        }
        return book;
    }

    public PagingResponse<Book> findAll(SearchDto params) {
        params.setDomainType("book");
        List<Book> list = null;
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        if (params.shouldIUseNaverBookApi()) {
            list = (List<Book>) (Object) Converter.toDto(bookOpenApiService.fetchAllFromNaver(params));
        } else if (params.shouldIUseAladinBookApi()) {
            list = (List<Book>) (Object) Converter.toDto(bookOpenApiService.fetchAllFromAladin(params));
        } else {
            list = findAllFromDB(params);
        }

        return new PagingResponse<>(list, params.getPagination());
    }

    public List<Book> findAllFromDB(SearchDto params) {
        params.setPaging(bookMapper.count(params));
        return bookMapper.findAllWithArticles(params);
    }

    public Long insert(BookRequestDto bookRequestDto) {
        Book book = bookRequestDto.toDomain();
        bookMapper.insert(book);
        return book.getId();
    }

    public Long update(Long id, BookUpdateRequestDto bookRequestDto) {
        Book book = bookMapper.findById(id);
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
        bookMapper.update(book);
        return id;
    }

    public void delete(Long id) {
        bookMapper.findById(id);
        bookMapper.delete(id);
    }
}
