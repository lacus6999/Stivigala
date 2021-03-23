package com.stivigala.wolt.dbo.delivery;

import com.stivigala.wolt.dbo.meal.Meal;
import com.stivigala.wolt.dbo.restaurant.Restaurant;
import com.stivigala.wolt.dbo.user.WoltUser;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
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
    private WoltUser user;

    @OneToMany(mappedBy = "delivery")
    private List<Meal> meals;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

}
