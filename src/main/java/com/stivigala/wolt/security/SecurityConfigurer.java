package com.stivigala.wolt.security;

import org.springframework.beans.factory.annotation.Autowired;
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
    private final WoltAuthSuccessHandler successHandler;

    public SecurityConfigurer(DataSource dataSource, WoltAuthSuccessHandler successHandler) {
        this.dataSource = dataSource;
        this.successHandler = successHandler;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT username, password, enabled\n"
                                + "FROM wolt_user\n "
                                + "WHERE username = ?"
                )
                .authoritiesByUsernameQuery(
                        "SELECT username, authority\n"
                                + "FROM authority\n"
                                + "WHERE username = ?"
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
                .formLogin().loginPage("/login")
                .successHandler(successHandler);

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
