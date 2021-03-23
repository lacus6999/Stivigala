package com.stivigala.wolt.dbo.authority;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    private String username;

    @Enumerated(EnumType.STRING)
    private AuthorityType authority;

}