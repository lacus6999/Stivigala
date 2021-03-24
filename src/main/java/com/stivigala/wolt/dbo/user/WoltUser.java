package com.stivigala.wolt.dbo.user;

import com.stivigala.wolt.dbo.address.Address;
import com.stivigala.wolt.dbo.restaurant.Restaurant;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WoltUser {

    @Id
    private String username;

    @NotNull
    private String password;

    private String fullName;

    @NotNull
    private Boolean enabled;

    private String phone;

    @OneToMany(mappedBy = "owner")
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "woltUser")
    private List<Address> addresses;

}
