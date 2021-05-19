package com.stivigala.wolt.controller.customer;

import com.stivigala.wolt.dbo.delivery.Delivery;
import com.stivigala.wolt.dbo.restaurant.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainCustomerSiteController {

    private final RestaurantRepository restaurantRepository;
    private final CustomerService customerService;


    public MainCustomerSiteController(RestaurantRepository restaurantRepository, CustomerService customerService) {
        this.restaurantRepository = restaurantRepository;
        this.customerService = customerService;
    }

    @GetMapping("/customer/getMainCustomerSite")
    public String getMainCustomerSite(Model model) {
        model.addAttribute("restaurantList", restaurantRepository.findAll());
        return "customer/mainCustomerSite";
    }

    @GetMapping("/customer/myDeliveries")
    public String getMyDeliveries(Model model) throws Exception {
        List<Delivery> myDeliveries = customerService.getMyDeliveries();
        model.addAttribute("myDeliveries", myDeliveries);
        return "/customer/myDeliveriesPage";
    }
}
