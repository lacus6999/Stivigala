package com.stivigala.wolt.dbo.restaurant;

import com.stivigala.wolt.dbo.user.WoltUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    List<Restaurant> findAllByOwner(WoltUser owner);

}
