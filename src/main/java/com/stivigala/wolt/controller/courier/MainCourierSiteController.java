package com.stivigala.wolt.controller.courier;

import com.stivigala.wolt.dbo.delivery.DeliveryRepository;
import com.stivigala.wolt.dbo.user.WoltUser;
import com.stivigala.wolt.service.user.WoltUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainCourierSiteController {

    private final DeliveryRepository deliveryRepository;

    private final WoltUserService woltUserService;


    public MainCourierSiteController(DeliveryRepository deliveryRepository, WoltUserService woltUserService) {
        this.deliveryRepository = deliveryRepository;
        this.woltUserService = woltUserService;
    }

    @PostMapping("/courier/addAvailability")
    public String addAvailability(String availability) throws Exception {
        WoltUser woltUser = woltUserService.getCurrentAuthenticatedUser();
        woltUser.setAvailability(availability);
        woltUserService.updateUser(woltUser);
        return "/courier/mainCourierSite";
    }

    @GetMapping("/courier/mainCourierSite")
    public String getMainCustomerSite(Model model) throws Exception {
        if(woltUserService.getCurrentAuthenticatedUser().getAvailability() != null) {
            model.addAttribute("deliveryList", deliveryRepository.findAllByCourier(woltUserService.getCurrentAuthenticatedUser()));
            return "courier/mainCourierSite";
        } else {
            return "courier/addAvailability";
        }
    }
}
