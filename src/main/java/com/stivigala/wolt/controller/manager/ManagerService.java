package com.stivigala.wolt.controller.manager;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.address.AddressRepository;
import com.stivigala.wolt.dbo.delivery.Delivery;
import com.stivigala.wolt.dbo.delivery.DeliveryRepository;
import com.stivigala.wolt.dbo.meal.Meal;
import com.stivigala.wolt.dbo.meal.MealRepository;
import com.stivigala.wolt.dbo.restaurant.Restaurant;
import com.stivigala.wolt.dbo.restaurant.RestaurantRepository;
import com.stivigala.wolt.dbo.user.WoltUserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerService {

    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final DeliveryRepository deliveryRepository;
    private final MealRepository mealRepository;

    private final WoltUserService woltUserService;

    public ManagerService(RestaurantRepository restaurantRepository, AddressRepository addressRepository, WoltUserService woltUserService, DeliveryRepository deliveryRepository, MealRepository mealRepository) {
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
        this.woltUserService = woltUserService;
        this.deliveryRepository = deliveryRepository;
        this.mealRepository = mealRepository;
    }

    public void addNewRestaurant(@NotNull HttpServletRequest request) throws Exception {
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
    }

    public Map<Restaurant, List<Delivery>> getRestaurantsWithDeliveries() throws Exception {
        Map<Restaurant, List<Delivery>> restaurantDeliveryMap = new HashMap<>();
        List<Restaurant> restaurants = restaurantRepository.findAllByOwner(woltUserService.getCurrentAuthenticatedUser());
        for (Restaurant restaurant : restaurants) {
            restaurantDeliveryMap.put(restaurant, deliveryRepository.findAllByRestaurant(restaurant));
        }
        return restaurantDeliveryMap;
    }

    public List<Restaurant> findAllRestaurants() throws Exception {
        return restaurantRepository.findAllByOwner(woltUserService.getCurrentAuthenticatedUser());
    }

    public Restaurant getRestaurantById(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public Meal getMealById(int id) {
        return mealRepository.findById(id).orElse(null);
    }

    public Delivery getDeliveryById(int id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    public void addNewMeal(HttpServletRequest request) {
        mealRepository.save(new Meal(
                null,
                request.getParameter("time"),
                restaurantRepository.findById(Integer.parseInt(request.getParameter("restaurantId"))).orElse(null),
                null,
                request.getParameter("menu"),
                request.getParameter("food")
        ));
    }
}
