package com.stivigala.wolt.controller.customer;

import com.stivigala.wolt.dbo.restaurant.Restaurant;
import com.stivigala.wolt.dbo.restaurant.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final RestaurantRepository restaurantRepository;

    public CustomerService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant findRestaurantById(Integer id) {
        return restaurantRepository.findById(id).orElse(null);
    }
}
