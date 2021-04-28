package com.stivigala.wolt.dbo.address;

import com.stivigala.wolt.dbo.user.WoltUser;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;

    @ManyToOne
    @JoinColumn(name = "username")
    private WoltUser woltUser;

}
