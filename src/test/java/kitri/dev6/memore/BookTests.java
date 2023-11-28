package kitri.dev6.memore;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.BookRequestDto;
import kitri.dev6.memore.dto.BookUpdateRequestDto;
import kitri.dev6.memore.repository.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class BookTests {
    @LocalServerPort
    private int port;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private TestRestTemplate restTemplate;
    private Long insertTestId;

    @AfterEach
    public void tearDown() throws Exception {
        bookMapper.deleteById(insertTestId);
    }

    @Test
    @DisplayName("도서 저장(성공)")
    void insertTest() throws Exception {
        // Given
        String title = "test@naver.com";
        Long categoryId = 1L;
        Long memberId = 1L;
        String isbn = "123123";
        String isbn13 = "123123";
        String cover = "path/to/image.jpg";
        String author = "김작가";
        String publisher = "세계출판사";
        LocalDate publishedDate = LocalDate.now();

        BookRequestDto bookRequestDto = BookRequestDto.builder()
                .categoryId(categoryId)
                .memberId(memberId)
                .title(title)
                .cover(cover)
                .author(author)
                .isbn(isbn)
                .isbn13(isbn13)
                .publisher(publisher)
                .publishedDate(publishedDate)
                .build();

        String url = "http://localhost:" + port + "/api/books";

        // When
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, bookRequestDto, Long.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        insertTestId = responseEntity.getBody();
        log.info("도서 저장 (성공) / 생성 ID :" + insertTestId);

        Book book = bookMapper.findById(responseEntity.getBody()).get();
        assertThat(book.getTitle()).isEqualTo(title);
        assertThat(book.getAuthor()).isEqualTo(author);
    }


    @Test
    @DisplayName("도서 수정(성공)")
    void updateTest() throws Exception {
        // Given

        Book book = Book.builder()
                .categoryId(1L)
                .memberId(1L)
                .title("테스트 도서")
                .isbn("00000000")
                .isbn13("00000000")
                .author("김작가")
                .publisher("세계출판사")
                .publishedDate(LocalDate.now())
                .approved(false)
                .build();

        bookMapper.insert(book);
        insertTestId = book.getId();
        String expectedTitle = "테스트유저2";
        String expectedAuthor = "이작가";
        String expectedIsbn = "11111111";
        String expectedIsbn13 = "11111111";
        String expectedPublisher = "이세계출판사";
        LocalDate expectedPublishedDate = LocalDate.now();
        Boolean expectedApproved = true;

        BookUpdateRequestDto requestDto = BookUpdateRequestDto.builder()
                .title(expectedTitle)
                .author(expectedAuthor)
                .isbn(expectedIsbn)
                .isbn13(expectedIsbn13)
                .publisher(expectedPublisher)
                .approved(expectedApproved)
                .publishedDate(expectedPublishedDate)
                .build();

        String url = "http://localhost:" + port + "/api/books/" + insertTestId;
        HttpEntity<BookUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        log.info("requestDto approved" + requestDto.getApproved());

        // When
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(insertTestId);
        log.info("도서 수정(성공) / 업데이트 ID :" + insertTestId);
        Book result = bookMapper.findById(insertTestId).get();
        assertThat(result.getTitle()).isEqualTo(expectedTitle);
        assertThat(result.getAuthor()).isEqualTo(expectedAuthor);
        assertThat(result.getIsbn()).isEqualTo(expectedIsbn);
        assertThat(result.getIsbn13()).isEqualTo(expectedIsbn13);
        assertThat(result.getPublisher()).isEqualTo(expectedPublisher);
        assertThat(result.getApproved()).isEqualTo(expectedApproved);
        assertThat(result.getPublishedDate()).isEqualTo(expectedPublishedDate);
    }
}

