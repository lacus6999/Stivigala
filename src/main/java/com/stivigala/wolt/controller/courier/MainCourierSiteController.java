package com.stivigala.wolt.controller.courier;

import com.stivigala.wolt.dbo.availability.Availability;
import com.stivigala.wolt.dbo.availability.AvailabilityRepository;
import com.stivigala.wolt.dbo.delivery.DeliveryRepository;
import com.stivigala.wolt.dbo.user.WoltUser;
import com.stivigala.wolt.service.user.WoltUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainCourierSiteController {

    private final AvailabilityRepository availabilityRepository;

    private final DeliveryRepository deliveryRepository;

    private final WoltUserService woltUserService;


    public MainCourierSiteController(AvailabilityRepository availabilityRepository, DeliveryRepository deliveryRepository, WoltUserService woltUserService) {
        this.availabilityRepository = availabilityRepository;
        this.deliveryRepository = deliveryRepository;
        this.woltUserService = woltUserService;
    }

    @PostMapping("/courier/addAvailability")
    public String addAvailability(
            Availability availability
    ) throws Exception {
        availabilityRepository.save(availability);
        WoltUser woltUser = woltUserService.getCurrentAuthenticatedUser();
        woltUser.setAvailability(availability);
        woltUserService.updateUser(woltUser);
        return "redirect:/courier/getMainCourierSite";
    }

    @GetMapping("/courier/getMainCourierSite")
    public String getMainCustomerSite(Model model) throws Exception {
        if (woltUserService.getCurrentAuthenticatedUser().getAvailability() != null) {
            WoltUser woltUser = woltUserService.getCurrentAuthenticatedUser();
            model.addAttribute("deliveryList", deliveryRepository.findAllByCourier(woltUser));
            model.addAttribute("availability", woltUser.getAvailability());
            return "courier/mainCourierSite";
        } else {
            return "courier/addAvailability";
        }
    }
}
