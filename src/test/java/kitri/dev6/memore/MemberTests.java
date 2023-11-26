package kitri.dev6.memore;

import kitri.dev6.memore.dto.MemberRequestDto;
import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.repository.MemberMapper;
import lombok.extern.slf4j.Slf4j;
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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MemberTests {
    @LocalServerPort
    private int port;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("회원 저장(성공)")
    void insertTest() throws Exception {
        // Given
        String email = "test@naver.com";
        String name = "테스트유저";
        String number = "01088889999";
        String password = "1234";
        String picture = "/path/";
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .email(email)
                .name(name)
                .number(number)
                .password(password)
                .picture(picture)
                .build();
        String url = "http://localhost:" + port + "/api/members/join";

        // When
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, memberRequestDto, Long.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.info("회원 저장 (성공) / 생성 ID :" + responseEntity.getBody());

        Member member = memberMapper.findById(responseEntity.getBody());
        assertThat(member.getEmail()).isEqualTo(email);
        assertThat(member.getNumber()).isEqualTo(number);
    }
}

