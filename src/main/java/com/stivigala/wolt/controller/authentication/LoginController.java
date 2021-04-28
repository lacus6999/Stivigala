package com.stivigala.wolt.controller.authentication;


import com.stivigala.wolt.dbo.user.WoltUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "authentication/loginPage";
    }

}
