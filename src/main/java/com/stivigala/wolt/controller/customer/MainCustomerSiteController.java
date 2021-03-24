package com.stivigala.wolt.controller.customer;

import com.stivigala.wolt.dbo.restaurant.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainCustomerSiteController {

    private final RestaurantRepository restaurantRepository;


    public MainCustomerSiteController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @GetMapping("/customer/mainCustomerSite")
    public String getMainCustomerSite(Model model) {
        model.addAttribute("restaurantList", restaurantRepository.findAll());
        return "customer/mainCustomerSite";
    }
}
