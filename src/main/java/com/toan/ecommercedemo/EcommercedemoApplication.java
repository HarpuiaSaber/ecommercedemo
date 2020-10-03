package com.toan.ecommercedemo;

import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.services.impl.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.toan.ecommercedemo.daos")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class EcommercedemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommercedemoApplication.class, args);
    }

    @Bean
    public AuditorAware<User> auditorAware() {
        return new AuditorAwareImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
