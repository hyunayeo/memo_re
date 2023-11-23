package kitri.dev6.memore.service;

import kitri.dev6.memore.dto.BookRequest;
import kitri.dev6.memore.entity.Book;
import kitri.dev6.memore.repository.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;

    public Book findById(Long id) {
        return bookMapper.findById(id);
    }

    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    public void create(BookRequest bookRequest) {
        Book book = Book.builder()
                .category_id(bookRequest.getCategory_id())
                .member_id(bookRequest.getMember_id())
                .title(bookRequest.getTitle())
                .isbn(bookRequest.getIsbn())
                .isbn13(bookRequest.getIsbn13())
                .cover(bookRequest.getCover())
                .link(bookRequest.getLink())
                .description(bookRequest.getDescription())
                .author(bookRequest.getAuthor())
                .publisher(bookRequest.getPublisher())
                .published_date(bookRequest.getPublished_date())
                .approved(bookRequest.isApproved())
                .build();
        bookMapper.create(book);
    }
}
