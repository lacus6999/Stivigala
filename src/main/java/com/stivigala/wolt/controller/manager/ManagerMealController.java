package com.stivigala.wolt.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManagerMealController {

    private final ManagerService managerService;

    public ManagerMealController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/manager/meal/{id}")
    public String showMealDetailsPage(Model model, @PathVariable String id) {
        model.addAttribute("meal", managerService.getMealById(Integer.parseInt(id)));

        return "/manager/mealDetailsPage";
    }

    @PostMapping("/manager/meal/addNewMealPage")
    public String showNewMealPage(HttpServletRequest request, Model model) {
        model.addAttribute("restaurantId", request.getParameter("restaurantId"));
        return "/manager/addNewMealPage";
    }

    @PostMapping("/manager/updateDiscount")
    public String updateDiscount(double discount, Integer mealId) {
        managerService.updateDiscount(mealId, discount);
        return "redirect:/manager/meal/" + mealId;
    }

    @PostMapping("/manager/meal/addNew")
    public String addNewMeal(HttpServletRequest request) {
        managerService.addNewMeal(request);
        return "redirect:/manager/restaurant/" + request.getParameter("restaurantId");
    }
}
