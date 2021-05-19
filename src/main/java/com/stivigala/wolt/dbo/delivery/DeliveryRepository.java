package com.stivigala.wolt.dbo.delivery;

import com.stivigala.wolt.dbo.restaurant.Restaurant;
import com.stivigala.wolt.dbo.user.WoltUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Integer> {

    List<Delivery> findAllByCourier(WoltUser courier);

    List<Delivery> findAllByRestaurant(Restaurant restaurant);

    List<Delivery> findAllByOrderedBy(WoltUser currentUser);
}
