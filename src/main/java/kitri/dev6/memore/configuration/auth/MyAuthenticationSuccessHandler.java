package kitri.dev6.memore.configuration.auth;

import kitri.dev6.memore.configuration.auth.dto.SessionUser;
import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.repository.MemberMapper;
import kitri.dev6.memore.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    public final Integer SESSION_TIMEOUT_IN_SECONDS = 60 * 300; // 30m
    String REDIRECT_URI = "http://localhost:3000/";
    public final MemberService memberService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);

        // 프론트에서 로그인 정보 확인을 위한 쿠키 생성
        String memberId = Long.toString(memberService.findIdByEmail(oAuth2User.getAttribute("email")));
        Cookie userCookie = new Cookie("memberId", memberId);
        userCookie.setMaxAge(SESSION_TIMEOUT_IN_SECONDS);
        userCookie.setDomain("localhost");
        userCookie.setPath("/");
        response.addCookie(userCookie);

        response.sendRedirect(UriComponentsBuilder.fromUriString(REDIRECT_URI)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString());
        log.info(oAuth2User.toString());
    }

}
