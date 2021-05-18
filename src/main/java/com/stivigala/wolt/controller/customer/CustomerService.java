package com.stivigala.wolt.controller.customer;

import com.stivigala.wolt.dbo.delivery.DeliveryStatus;
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

    public List<Meal> getAllMealByRestaurantId(Integer id) {
        return mealRepository.findAllByRestaurantId(id);
    }

    public List<Meal> findAllMealsByIds(List<Integer> ids) {
        return mealRepository.findAllByIdIn(ids);
    }

    public double calculatePrice(List<Meal> meals) throws Exception {
        Double price = 0.0;
        for (Meal meal : meals) {
            price += meal.getPrice() * (1 - (meal.getDiscount() / 100));
        }

        return price;
    }

    public void order(List<Meal> meals, double price, boolean isHomeDelivery) throws Exception {
        deliveryRepository.save(new Delivery(
                null,
                false,
                isHomeDelivery,
                woltUserService.getCurrentAuthenticatedUser(),
                meals,
                meals.get(0).getRestaurant(),
                new Date(),
                null,
                price,
                DeliveryStatus.NOT_ASSIGNED
        ));

    }
}
