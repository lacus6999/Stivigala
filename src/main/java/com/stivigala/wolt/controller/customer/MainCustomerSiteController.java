package com.stivigala.wolt.controller.customer;

import com.stivigala.wolt.dbo.delivery.Delivery;
import com.stivigala.wolt.dbo.meal.Meal;
import com.stivigala.wolt.dbo.restaurant.Restaurant;
import com.stivigala.wolt.dbo.restaurant.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MainCustomerSiteController {

    private final RestaurantRepository restaurantRepository;


    public MainCustomerSiteController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/customer/mainCustomerSite")
    public String getMainCustomerSite(Model model) {
//        Restaurant restaurant = new Restaurant(
//                1,
//                "rest",
//                List.of(
//                        new Meal(1, "asd", null, null, "asd", "asd"),
//                        new Meal(2, "asd2", null, null, "asd2", "asd2")
//                ),
//                new ArrayList<Delivery>(),
//                null,
//                null
//        );
//        model.addAttribute("restaurantList", Collections.singletonList(restaurant));
        model.addAttribute("restaurantList", restaurantRepository.findAll());
        return "customer/mainCustomerSite";
    }
}
