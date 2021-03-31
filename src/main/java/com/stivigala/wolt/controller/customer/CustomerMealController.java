package com.stivigala.wolt.controller.customer;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.authority.AuthorityType;
import com.stivigala.wolt.dbo.delivery.DeliveryRepository;
import com.stivigala.wolt.dbo.meal.Meal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CustomerMealController {

    private final CustomerService customerService;

    public CustomerMealController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/restaurant/meal/{id}")
    public String showMealDetailsPage(Model model, @PathVariable String id) {
        model.addAttribute("meal", customerService.findMealById(Integer.parseInt(id)));

        return "/customer/mealDetailsPage";
    }

    @PostMapping("/customer/order")
    public String order(HttpServletRequest request) throws Exception {
        customerService.order(List.of(request.getParameterValues("addToCart")).stream().map(Integer::parseInt).collect(Collectors.toList()));

        return "redirect:/customer/mainCustomerSite";
    }
}
