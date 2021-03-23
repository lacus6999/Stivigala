package com.stivigala.wolt.controller.manager;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.address.AddressRepository;
import com.stivigala.wolt.dbo.restaurant.Restaurant;
import com.stivigala.wolt.dbo.restaurant.RestaurantRepository;
import com.stivigala.wolt.dbo.user.WoltUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class MainManagerSiteController {

    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;

    private final WoltUserService woltUserService;

    public MainManagerSiteController(RestaurantRepository restaurantRepository, WoltUserService woltUserService, AddressRepository addressRepository) {
        this.restaurantRepository = restaurantRepository;
        this.woltUserService = woltUserService;
        this.addressRepository = addressRepository;
    }

    @GetMapping("/restaurant/mainManagerSite")
    public String listUsers(Model model) throws Exception {
        model.addAttribute("restaurantList", restaurantRepository.findAllByOwner(woltUserService.getCurrentAuthenticatedUser()));
        return "manager/mainManagerSite";
    }

    @GetMapping("/restaurant/addNewSite")
    public String addNewRestaurantPage() {
        return "manager/addNewRestaurantPage";
    }

    @PostMapping("/restaurant/addNew")
    public String addNewRestaurant(HttpServletRequest request) throws Exception {

        Address address = new Address(null, request.getParameter("address"), null);
        addressRepository.save(address);
        restaurantRepository.save(new Restaurant(
                null,
                request.getParameter("name"),
                new ArrayList<>(),
                new ArrayList<>(),
                address,
                woltUserService.getCurrentAuthenticatedUser()
        ));

        return "redirect:/restaurant/mainManagerSite";
    }

}
