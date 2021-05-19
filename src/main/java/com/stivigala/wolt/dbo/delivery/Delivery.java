package com.stivigala.wolt.dbo.delivery;

import com.stivigala.wolt.dbo.meal.Meal;
import com.stivigala.wolt.dbo.restaurant.Restaurant;
import com.stivigala.wolt.dbo.user.WoltUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean isDelivered;

    private Boolean isHomeDelivery;

    @OneToOne
    @JoinColumn(name="orderedBy", referencedColumnName = "username")
    private WoltUser orderedBy;

    @OneToMany(mappedBy = "delivery")
    private List<Meal> meals;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedAt;

    @OneToOne
    @JoinColumn(name="courier", referencedColumnName = "username")
    private WoltUser courier;

    private Double price;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private Integer time;

}


