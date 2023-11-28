package kitri.dev6.memore;

import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.dto.MemberRequestDto;
import kitri.dev6.memore.dto.MemberUpdateRequestDto;
import kitri.dev6.memore.repository.MemberMapper;
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
    private Long insertTestId;

    @AfterEach
    public void tearDown() throws Exception {
        memberMapper.deleteById(insertTestId);
    }

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
        String url = "http://localhost:" + port + "/api/members";

        // When
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, memberRequestDto, Long.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        insertTestId = responseEntity.getBody();
        log.info("회원 저장 (성공) / 생성 ID :" + insertTestId);

        Member member = memberMapper.findById(insertTestId).get();
        assertThat(member.getEmail()).isEqualTo(email);
        assertThat(member.getNumber()).isEqualTo(number);
    }


    @Test
    @DisplayName("회원 수정(성공)")
    void updateTest() throws Exception {
        // Given
        Member member = Member.builder()
                .email("test@naver.com")
                .name("테스트유저")
                .password("1234")
                .number("010-1234-1234")
                .picture("/path/to/image.jpg")
                .build();
        memberMapper.insert(member);
        insertTestId = member.getId();
        String expectedName = "테스트유저2";
        String expectedPassword = "12345";
        String expectedNumber = "010-0000-1111";
        String expectedPicture = "";
        MemberUpdateRequestDto requestDto = MemberUpdateRequestDto.builder()
                .name(expectedName)
                .password(expectedPassword)
                .picture(expectedPicture)
                .number(expectedNumber)
                .build();

        String url = "http://localhost:" + port + "/api/members/" + insertTestId;
        HttpEntity<MemberUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // When
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(insertTestId);
        log.info("회원 수정(성공) / 업데이트 ID :" + insertTestId);
        Member result = memberMapper.findById(insertTestId).get();
        assertThat(result.getName()).isEqualTo(expectedName);
        assertThat(result.getPassword()).isEqualTo(expectedPassword);
        assertThat(result.getNumber()).isEqualTo(expectedNumber);
        assertThat(result.getPicture()).isEqualTo(expectedPicture);
    }
}
