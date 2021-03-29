package com.stivigala.wolt.controller.customer;

import com.stivigala.wolt.dbo.delivery.Delivery;
import com.stivigala.wolt.dbo.meal.Meal;
import com.stivigala.wolt.dbo.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerRestaurantController {

    private final CustomerService customerService;

    public CustomerRestaurantController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/restaurant/{id}")
    public String showRestaurantDetailsPage(@PathVariable Integer id, Model model) {
//        model.addAttribute("restaurant", new Restaurant(
//                1,
//                "rest",
//                List.of(
//                        new Meal(1, "asd", null, null, "asd", "asd"),
//                        new Meal(2, "asd2", null, null, "asd2", "asd2")
//                ),
//                new ArrayList<Delivery>(),
//                null,
//                null
//        ));
        model.addAttribute("restaurant", customerService.findRestaurantById(id));
        model.addAttribute("mealList", customerService.getAllMealByRestaurantId(id));

        return "customer/restaurantDetailsPage";
    }

}
