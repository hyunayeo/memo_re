package kitri.dev6.memore;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.ArticleRequestDto;
import kitri.dev6.memore.repository.ArticleMapper;
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
public class AtricleTests {
    @LocalServerPort
    private int port;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TestRestTemplate restTemplate;
    private Long insertTestID;

    @AfterEach
    public void tearDown() throws Exception {
        articleMapper.deleteById(insertTestID);
    }

    @Test
    @DisplayName("게시물 저장(성공)")
    void insertTest() throws Exception {
        // Given
        Long memberId = 7L;
        Long bookId = 1L;
        String title = "test code insert";
        String content = "123123";
        boolean done= true;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        int ratingScore = 2;
        boolean isHide = true;


        ArticleRequestDto articleRequestDto = ArticleRequestDto.builder()
                .memberId(memberId)
                .bookId(bookId)
                .title(title)
                .content(content)
                .done(done)
                .startDate(startDate)
                .endDate(endDate)
                .ratingScore(ratingScore)
                .isHide(isHide)
                .build();
        String url = "http://localhost:" + port + "/api/articles";

        // When
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, articleRequestDto, Long.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        insertTestID = responseEntity.getBody();
        log.info("게시글 저장 (성공) / 생성 ID :" + insertTestID);

        Article article = articleMapper.findById(insertTestID).get();
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getBookId()).isEqualTo(bookId);
    }
}
