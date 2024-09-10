package craftcode.workshop.beer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public Authentication userInfo(Authentication authentication) {
        return authentication;
    }
}
