package com.stivigala.wolt.dbo.user;

import com.stivigala.wolt.dbo.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id
    private String userName;

    private String password;

    private Boolean enabled;

    @OneToMany(mappedBy = "users")
    private List<Address> addresses;

}
