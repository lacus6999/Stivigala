package com.stivigala.wolt.dbo.confirmationtoken;

import com.stivigala.wolt.dbo.user.WoltUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    private String token;

    @OneToOne
    @JoinColumn(name = "woltuser", referencedColumnName = "username")
    private WoltUser woltUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expireAt;

}



