package kitri.dev6.memore.configuration.auth;

import kitri.dev6.memore.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Value("${redirect.uri}")
    String REDIRECT_URI;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/**", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and().logout()
                .logoutUrl("/api/logout")
                .logoutSuccessUrl(REDIRECT_URI)
                .and().oauth2Login().successHandler(myAuthenticationSuccessHandler)
                .userInfoEndpoint().userService(customOAuth2UserService);
    }
}
