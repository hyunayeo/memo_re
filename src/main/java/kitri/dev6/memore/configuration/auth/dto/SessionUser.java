package kitri.dev6.memore.configuration.auth.dto;

import kitri.dev6.memore.domain.Member;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.io.Serializable;
@Getter
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionUser implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String picture;
    public SessionUser(Member user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
