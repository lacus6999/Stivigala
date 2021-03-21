package com.stivigala.wolt.dbo.address;

import com.stivigala.wolt.dbo.user.WoltUser;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Integer id;

    private String address;

    @ManyToOne
    @JoinColumn(name = "userName", nullable = false)
    private WoltUser woltUser;

}
