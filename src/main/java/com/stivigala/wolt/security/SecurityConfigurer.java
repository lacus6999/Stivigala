package com.stivigala.wolt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public SecurityConfigurer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT user_name, password, enabled\n"
                                + "FROM users\n "
                                + "WHERE user_name = ?"
                )
                .authoritiesByUsernameQuery(
                        "SELECT user_name, authority\n"
                                + "FROM authority\n"
                                + "WHERE user_name = ?"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/customer").hasAnyAuthority("ADMIN", "MANAGER", "CUSTOMER")
                .antMatchers("/manager").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers("/admin").hasAnyAuthority("ADMIN")
                .antMatchers("/registration").permitAll()
                .antMatchers("/**").permitAll()

                //TODO ne felejtsük el átírni bemutatáskor
                .antMatchers("/h2-console/**").permitAll()
                .and()
                .formLogin().loginPage("/login");

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
