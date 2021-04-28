package com.stivigala.wolt.controller.customer;

import com.stivigala.wolt.dbo.delivery.Delivery;
import com.stivigala.wolt.dbo.delivery.DeliveryRepository;
import com.stivigala.wolt.dbo.meal.Meal;
import com.stivigala.wolt.dbo.meal.MealRepository;
import com.stivigala.wolt.dbo.restaurant.Restaurant;
import com.stivigala.wolt.dbo.restaurant.RestaurantRepository;
import com.stivigala.wolt.service.user.WoltUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;
    private final DeliveryRepository deliveryRepository;
    private final WoltUserService woltUserService;

    public Restaurant findRestaurantById(Integer id) {
        return restaurantRepository.findById(id).orElse(null);
    }
    public Meal findMealById(Integer id) {
        return mealRepository.findById(id).orElse(null);
    }
    public List<Meal> getAllMealByRestaurantId(Integer id) {return mealRepository.findAllByRestaurantId(id);}

    public void order(List<Integer> ids) throws Exception {
        List<Meal> meals = mealRepository.findAllByIdIn(ids);

        Double price = 0.0;

        for (Meal meal : meals) {
            price += meal.getPrice();
        }

        deliveryRepository.save(new Delivery(
                null,
                false,
                woltUserService.getCurrentAuthenticatedUser(),
                meals,
                meals.get(0).getRestaurant(),
                new Date(),
                null,
                price
        ));
    }
}
