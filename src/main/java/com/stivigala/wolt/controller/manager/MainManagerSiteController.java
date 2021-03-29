package com.stivigala.wolt.controller.manager;

import com.stivigala.wolt.dbo.user.WoltUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainManagerSiteController {

    private final ManagerService managerService;
    private final WoltUserService woltUserService;

    public MainManagerSiteController(ManagerService managerService, WoltUserService woltUserService) {
        this.managerService = managerService;
        this.woltUserService = woltUserService;
    }

    @GetMapping("/manager/mainManagerSite")
    public String showMainManagerPage(Model model) throws Exception {
        model.addAttribute("restaurants", managerService.findAllRestaurants());
        model.addAttribute("currentUsername", woltUserService.getCurrentAuthenticatedUser().getUsername());
        return "manager/mainManagerSite";
    }

}
