package com.stivigala.wolt.dbo.meal;

import com.stivigala.wolt.dbo.delivery.Delivery;
import com.stivigala.wolt.dbo.restaurant.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String time;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private String menu;

    private String food;

    private Double price;

}
