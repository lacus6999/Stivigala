package com.stivigala.wolt.dbo.order;

import com.stivigala.wolt.dbo.meal.Meal;
import com.stivigala.wolt.dbo.restaurant.Restaurant;
import com.stivigala.wolt.dbo.user.Users;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Delivery {

    @Id
    @GeneratedValue
    private Integer id;

    private Boolean isDelivered;

    @OneToOne
    @JoinColumn(name="user_name", referencedColumnName = "userName")
    private Users users;

    @OneToMany(mappedBy = "delivery")
    private List<Meal> meals;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

}
