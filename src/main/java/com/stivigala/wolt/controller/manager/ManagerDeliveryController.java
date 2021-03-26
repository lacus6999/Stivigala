package com.stivigala.wolt.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManagerDeliveryController {

    private final ManagerService managerService;

    public ManagerDeliveryController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/manager/delivery/{id}")
    public String showDeliveryDetailsPage(Model model, @PathVariable String id) {
        model.addAttribute("delivery", managerService.getDeliveryById(Integer.parseInt(id)));
        model.addAttribute("couriers", managerService.findAllCouriers());

        return "/manager/deliveryDetailsPage";
    }

    @PostMapping("/manager/delivery/assignCourierToDelivery")
    public String assignCourierToDelivery(HttpServletRequest request) {
        managerService.assignCourierToDelivery(request);
        int restaurantId = managerService.findRestaurantIdFromDeliveryId(Integer.parseInt(request.getParameter("deliveryId")));

        return "redirect:/manager/restaurant/" + restaurantId;
    }

}
