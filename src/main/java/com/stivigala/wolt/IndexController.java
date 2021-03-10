package com.stivigala.wolt;

import com.stivigala.wolt.dbo.user.UserRepository;
import com.stivigala.wolt.dbo.user.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public IndexController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping
    public String indexPage() {
        return "index.html";
    }

    @RequestMapping("/registration")
    public String registrationPage() {
        return "register.html";
    }


    @PostMapping("/register")
    public String registerUser(Users user) {

        user.setUserName(user.getUserName());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        System.out.println("kascsacsadsasdsadadsa");
        return "<p>Thanks :)</p>";
    }
}
