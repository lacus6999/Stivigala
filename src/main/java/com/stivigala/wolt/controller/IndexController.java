package com.stivigala.wolt.controller;

import com.stivigala.wolt.dbo.user.WoltUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    private final WoltUserService woltUserService;

    public IndexController(WoltUserService woltUserService) {
        this.woltUserService = woltUserService;
    }

    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }

    @GetMapping(value = "/confirmation")

    public String confirmation(@RequestParam String token) {
        try {
            woltUserService.confirmUserViaToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "authentication/verified";
    }

}
