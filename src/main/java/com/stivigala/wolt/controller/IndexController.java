package com.stivigala.wolt.controller;

import com.stivigala.wolt.dbo.user.WoltUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private final WoltUserRepository woltUserRepository;

    public IndexController(WoltUserRepository woltUserRepository) {
        this.woltUserRepository = woltUserRepository;
    }

    @GetMapping(value = "/")
    public String indexPage() {
        return "oldIndex";
    }

    @GetMapping(value = "/woltuser/{username}")
    public String getUserData(@PathVariable String username, Model model) {

        model.addAttribute("woltUser", woltUserRepository.findById(username));
        return "woltUserData";
    }

    @GetMapping("/users/list")
    public String listUsers(Model model) {
        model.addAttribute("userList", woltUserRepository.findAll());
        return "userList";
    }
}
