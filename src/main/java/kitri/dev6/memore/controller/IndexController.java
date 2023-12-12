package kitri.dev6.memore.controller;

import kitri.dev6.memore.configuration.auth.LoginUser;
import kitri.dev6.memore.configuration.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("")
public class IndexController {
    @GetMapping("/api/test")
    public String test(@LoginUser SessionUser user){
        if (user != null){
            return user.getEmail();
        }
        return "no";
    }
    @GetMapping("/api")
    public String test2(@LoginUser SessionUser user){
        if (user != null){
            return user.toString();
        }
        return "no";
    }
}
