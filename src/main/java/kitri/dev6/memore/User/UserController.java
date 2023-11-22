package kitri.dev6.memore.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/app/")
public class UserController {
    @Autowired
    UserService userService;


}
