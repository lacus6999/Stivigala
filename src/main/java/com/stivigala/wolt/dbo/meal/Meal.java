package com.stivigala.wolt.dbo.meal;

import com.stivigala.wolt.dbo.order.Delivery;
import com.stivigala.wolt.dbo.restaurant.Restaurant;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Meal {

    @Id
    @GeneratedValue
    private Integer id;

    private Date time;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

}
