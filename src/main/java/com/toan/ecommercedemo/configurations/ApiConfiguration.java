package com.toan.ecommercedemo.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .antMatcher("/api/**").authorizeRequests()
                .antMatchers("/api/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/seller/**").hasAnyAuthority("SELLER", "ADMIN")
                .antMatchers("/api/customer/**").hasAnyAuthority("CUSTOMER", "ADMIN")
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

}
