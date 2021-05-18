package com.stivigala.wolt.controller.customer;

import com.stivigala.wolt.dbo.meal.Meal;
import com.stivigala.wolt.service.user.WoltUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CustomerMealController {

    private final CustomerService customerService;
    private final WoltUserService woltUserService;

    public CustomerMealController(CustomerService customerService, WoltUserService woltUserService) {
        this.customerService = customerService;
        this.woltUserService = woltUserService;
    }

    @GetMapping("/customer/restaurant/meal/{id}")
    public String showMealDetailsPage(Model model, @PathVariable String id) {
        model.addAttribute("meal", customerService.findMealById(Integer.parseInt(id)));

        return "/customer/mealDetailsPage";
    }

    @PostMapping("/customer/showDeliveryDetails")
    public String showDeliveryDetails(HttpServletRequest request, Model model) throws Exception {
        if(request.getParameterValues("addToCart") == null) {
            return "redirect:/customer/getMainCustomerSite";
        }
        List<Meal> meals = customerService.findAllMealsByIds(List.of(request.getParameterValues("addToCart")).stream().map(Integer::parseInt).collect(Collectors.toList()));
        double price = customerService.calculatePrice(meals);
        request.getServletContext().setAttribute("meals", meals);
        request.getServletContext().setAttribute("price", price);
        model.addAttribute("address", woltUserService.getCurrentAuthenticatedUser().getAddresses().get(0));

        return "/customer/deliveryDetailsPage";
    }

    @PostMapping("/customer/placeOrder")
    public String placeOrder(HttpServletRequest request, Boolean isHomeDelivery) throws Exception {
        ServletContext servletContext = request.getServletContext();
        if(isHomeDelivery != null)
            customerService.order((List<Meal>) servletContext.getAttribute("meals"), (double) servletContext.getAttribute("price") + 1000, true);
        else
            customerService.order((List<Meal>) servletContext.getAttribute("meals"), (double) servletContext.getAttribute("price"), false);
        servletContext.removeAttribute("meals");
        servletContext.removeAttribute("price");
        return "redirect:/customer/getMainCustomerSite";
    }

}
