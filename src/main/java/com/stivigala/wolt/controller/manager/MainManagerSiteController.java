package com.stivigala.wolt.controller.manager;

import com.stivigala.wolt.dbo.restaurant.Restaurant;
import com.stivigala.wolt.service.user.WoltUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class MainManagerSiteController {

    private final ManagerService managerService;
    private final WoltUserService woltUserService;

    public MainManagerSiteController(ManagerService managerService, WoltUserService woltUserService) {
        this.managerService = managerService;
        this.woltUserService = woltUserService;
    }

    @GetMapping("/manager/mainManagerSite")
    public String showMainManagerPage(Model model, HttpServletRequest request) throws Exception {
        initSession(request);
        model.addAttribute("restaurants", managerService.findAllRestaurantsByOwner());
        return "manager/mainManagerSite";
    }

    private void initSession(HttpServletRequest request) throws Exception {
    }

}
