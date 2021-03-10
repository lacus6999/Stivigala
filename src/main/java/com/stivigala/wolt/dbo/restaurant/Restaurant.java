package com.stivigala.wolt.dbo.restaurant;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.meal.Meal;
import com.stivigala.wolt.dbo.order.Delivery;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "restaurant")
    private List<Meal> meals;

    @OneToMany(mappedBy = "restaurant")
    private List<Delivery> deliveries;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
