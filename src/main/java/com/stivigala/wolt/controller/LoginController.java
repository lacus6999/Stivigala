package com.stivigala.wolt.controller;


import com.stivigala.wolt.dbo.user.WoltUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final WoltUserRepository woltUserRepository;

    public LoginController(WoltUserRepository woltUserRepository) {
        this.woltUserRepository = woltUserRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "authentication/loginPage";
    }

}
