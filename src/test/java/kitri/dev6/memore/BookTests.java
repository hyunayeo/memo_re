package kitri.dev6.memore;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.BookRequestDto;
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
    private Long insertTestID;

    @AfterEach
    public void tearDown() throws Exception{
        bookMapper.deleteById(insertTestID);
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

        String url = "http://localhost:" + port + "/api/books/create";

        // When
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, bookRequestDto, Long.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        insertTestID = responseEntity.getBody();
        log.info("도서 저장 (성공) / 생성 ID :" + insertTestID);

        Book book = bookMapper.findById(responseEntity.getBody()).get();
        assertThat(book.getTitle()).isEqualTo(title);
        assertThat(book.getAuthor()).isEqualTo(author);
    }
}

