package kitri.dev6.memore.controller;

import kitri.dev6.memore.configuration.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@RequiredArgsConstructor
@RestController("")
public class IndexController {
    private final HttpSession httpSession;
    @GetMapping("/")
    public String test(){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            return "ok";
        }
        return "no";
    }
    @GetMapping("/api")
    public String test2(){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            return "ok";
        }
        return "no";
    }
}
