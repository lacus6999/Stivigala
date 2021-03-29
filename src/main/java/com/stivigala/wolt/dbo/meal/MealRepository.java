package com.stivigala.wolt.dbo.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findAllByRestaurantId(Integer id);
    List<Meal> findAllByIdIn(List<Integer> ids);
}
