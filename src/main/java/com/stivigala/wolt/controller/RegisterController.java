package com.stivigala.wolt.controller;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.authority.AuthorityType;
import com.stivigala.wolt.dbo.user.WoltUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
public class RegisterController {

    private final WoltUserService woltUserService;

    public RegisterController(WoltUserService woltUserService) {
        this.woltUserService = woltUserService;
    }

    @GetMapping(value = "/registration")
    public String registrationPage() {
        return "registerPage";
    }

    @PostMapping("/register")
    public String registerUser(HttpServletRequest request) {
        woltUserService.register(
                request.getParameter("userName"),
                request.getParameter("password"),
                true,
                Collections.singletonList(new Address()),
                AuthorityType.valueOf(request.getParameter("authority").toUpperCase())
        );

        return "index";
    }
}
