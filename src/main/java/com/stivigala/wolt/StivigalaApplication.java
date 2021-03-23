package com.stivigala.wolt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class StivigalaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StivigalaApplication.class, args);
    }

}
