package com.stivigala.wolt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableJpaRepositories
public class StivigalaApplication implements CommandLineRunner {

    public StivigalaApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(StivigalaApplication.class, args);
    }

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) {

        jdbcTemplate.execute(
                "create table if not exists authority (" +
                        "user_name varchar(50) not null," +
                        "authority varchar(50) not null," +
                        "constraint fk_authorities_users foreign key(user_name) references wolt_user (user_name)" +
                        ");"
        );

    }

}
