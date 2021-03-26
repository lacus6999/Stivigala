package com.stivigala.wolt.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MainManagerSiteController {

    private final ManagerService managerService;

    public MainManagerSiteController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/manager/mainManagerSite")
    public String showMainManagerPage(Model model) throws Exception {
        model.addAttribute("restaurants", managerService.findAllRestaurants());
        return "manager/mainManagerSite";
    }

}
