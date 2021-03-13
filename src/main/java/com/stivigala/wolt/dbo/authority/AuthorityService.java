package com.stivigala.wolt.dbo.authority;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    private final JdbcTemplate jdbcTemplate;

    public AuthorityService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //TODO jpa-val jobb lenne (authority entity, idegen kulcs a StivigalaApplication osztály sql-je alapján)
    public void addAuthority(String userName, AuthorityType authorityType) {
        jdbcTemplate.execute(
                "INSERT INTO authority values ( '" + userName + "','" + authorityType.toString() + "')"
        );
    }

}
