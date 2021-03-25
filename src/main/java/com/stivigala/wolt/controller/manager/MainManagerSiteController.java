package com.stivigala.wolt.controller.manager;

import com.stivigala.wolt.dbo.user.WoltUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/manager/addNewRestaurantPage")
    public String showNewRestaurantPage() {
        return "manager/addNewRestaurantPage";
    }

    @PostMapping("/manager/restaurant/addNew")
    public String addNewRestaurant(HttpServletRequest request) throws Exception {
        managerService.addNewRestaurant(request);

        return "redirect:/manager/mainManagerSite";
    }

    @GetMapping("/manager/restaurant/{id}")
    public String showRestaurantDetailsPage(Model model, @PathVariable String id) {
        model.addAttribute("restaurant", managerService.getRestaurantById(Integer.parseInt(id)));

        return "/manager/restaurantDetailsPage";
    }

    @GetMapping("/manager/meal/{id}")
    public String showMealDetailsPage(Model model, @PathVariable String id) {
        model.addAttribute("meal", managerService.getMealById(Integer.parseInt(id)));

        return "/manager/mealDetailsPage";
    }

    @GetMapping("/manager/delivery/{id}")
    public String showDeliveryDetailsPage(Model model, @PathVariable String id) {
        model.addAttribute("delivery", managerService.getDeliveryById(Integer.parseInt(id)));

        return "/manager/deliveryDetailsPage";
    }

    @PostMapping("/manager/meal/addNewMealPage")
    public String showNewMealPage(HttpServletRequest request, Model model) {
        model.addAttribute("restaurantId", request.getParameter("restaurantId"));
        return "/manager/addNewMealPage";
    }

    @PostMapping("/manager/meal/addNew")
    public String addNewMeal(HttpServletRequest request) {
        managerService.addNewMeal(request);
        return "redirect:/manager/mainManagerSite";
    }




}
