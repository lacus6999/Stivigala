package com.stivigala.wolt.controller.authentication;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.authority.AuthorityType;
import com.stivigala.wolt.dbo.user.WoltUser;
import com.stivigala.wolt.service.user.WoltUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

@Controller
public class RegisterController {

    private final WoltUserService woltUserService;

    public RegisterController(WoltUserService woltUserService) {
        this.woltUserService = woltUserService;
    }

    @GetMapping(value = "/registration")
    public String registrationPage() {
        return "authentication/registerPage";
    }

    @PostMapping("/register")
    public String registerUser(WoltUser woltUser, String address, String authority) {
        woltUserService.register(woltUser, address, AuthorityType.valueOf(authority.toUpperCase()));
        return "authentication/verify";
    }
}
