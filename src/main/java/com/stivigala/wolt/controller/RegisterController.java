package com.stivigala.wolt.controller;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.authority.AuthorityType;
import com.stivigala.wolt.dbo.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/registration")
    public String registrationPage() {
        return "registerPage";
    }

    @PostMapping("/register")
    public String registerUser(HttpServletRequest request) {
        userService.register(
                request.getParameter("userName"),
                request.getParameter("password"),
                true,
                Collections.singletonList(new Address()),
                AuthorityType.valueOf(request.getParameter("authority").toUpperCase())
        );

        return "index";
    }
}
