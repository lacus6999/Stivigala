package com.stivigala.wolt.dbo.address;

import com.stivigala.wolt.dbo.user.WoltUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Integer id;

    private String address;

    @ManyToOne
    @JoinColumn(name = "username")
    private WoltUser woltUser;

}
