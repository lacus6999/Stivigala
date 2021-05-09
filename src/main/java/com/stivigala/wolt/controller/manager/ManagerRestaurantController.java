package com.stivigala.wolt.controller.manager;

import com.stivigala.wolt.dbo.user.WoltUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManagerRestaurantController {

    private final ManagerService managerService;
    private final WoltUserService userService;

    public ManagerRestaurantController(ManagerService managerService, WoltUserService userService) {
        this.managerService = managerService;
        this.userService = userService;
    }

    @GetMapping("/manager/restaurant/{id}")
    public String showRestaurantDetailsPage(Model model, @PathVariable Integer id) {
        model.addAttribute("restaurant", managerService.getRestaurantById(id));

        return "/manager/restaurantDetailsPage";
    }

    @GetMapping("/manager/addNewRestaurantPage")
    public String showNewRestaurantPage(Model model) throws Exception {
        model.addAttribute("currentUsername", userService.getCurrentAuthenticatedUser().getUsername());
        return "manager/addNewRestaurantPage";
    }

    @PostMapping("/manager/restaurant/addNew")
    public String addNewRestaurant(HttpServletRequest request) throws Exception {
        managerService.addNewRestaurant(request);

        return "redirect:/manager/mainManagerSite";
    }
}
