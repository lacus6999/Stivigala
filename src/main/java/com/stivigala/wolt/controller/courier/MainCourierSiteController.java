package com.stivigala.wolt.controller.courier;

import com.stivigala.wolt.dbo.delivery.DeliveryRepository;
import com.stivigala.wolt.dbo.user.WoltUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainCourierSiteController {

    private final DeliveryRepository deliveryRepository;

    private final WoltUserService woltUserService;


    public MainCourierSiteController(DeliveryRepository deliveryRepository, WoltUserService woltUserService) {
        this.deliveryRepository = deliveryRepository;
        this.woltUserService = woltUserService;
    }


    @GetMapping("/courier/mainCourierSite")
    public String getMainCustomerSite(Model model) throws Exception {
        model.addAttribute("deliveryList", deliveryRepository.findAllByCourier(woltUserService.getCurrentAuthenticatedUser()));
        return "courier/mainCourierSite";
    }
}
