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
        return "index";
    }

}
