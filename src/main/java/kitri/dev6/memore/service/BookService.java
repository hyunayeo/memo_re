package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.common.Converter;
import kitri.dev6.memore.dto.common.Pagination;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.BookRequestDto;
import kitri.dev6.memore.dto.response.BookResponseDto;
import kitri.dev6.memore.dto.request.BookUpdateRequestDto;
import kitri.dev6.memore.repository.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;

    public BookResponseDto findById(Long id) {
        Book book = bookMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다, id=" + id));
        return new BookResponseDto(book);
    }

    public PagingResponse<BookResponseDto> findAll(SearchDto params) {
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = bookMapper.count();
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<Book> list = bookMapper.findAll(params);
        // domain -> dto
        List<BookResponseDto> convertedList = (List<BookResponseDto>) (Object) Converter.domainListTodtoList(list);

        return new PagingResponse<>(convertedList, pagination);
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
