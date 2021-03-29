package com.stivigala.wolt.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManagerRestaurantController {

    private final ManagerService managerService;

    public ManagerRestaurantController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/manager/restaurant/{id}")
    public String showRestaurantDetailsPage(Model model, @PathVariable String id) {
        model.addAttribute("restaurant", managerService.getRestaurantById(Integer.parseInt(id)));

        return "/manager/restaurantDetailsPage";
    }

    @GetMapping("/manager/addNewRestaurantPage")
    public String showNewRestaurantPage() {
        return "manager/addNewRestaurantPage";
    }

    @PostMapping("/manager/restaurant/addNew")
    public String addNewRestaurant(HttpServletRequest request) throws Exception {
        managerService.addNewRestaurant(request);

        return "redirect:/manager/mainManagerSite";
    }
}
