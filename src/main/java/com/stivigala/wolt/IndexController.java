package com.stivigala.wolt;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.authority.AuthorityType;
import com.stivigala.wolt.dbo.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
public class IndexController {

    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
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
