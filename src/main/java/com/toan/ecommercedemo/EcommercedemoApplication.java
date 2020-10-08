package com.toan.ecommercedemo;

import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.services.impl.AuditorAwareImpl;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.modelmapper.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

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
        ModelMapper modelmapper = new ModelMapper();

        Provider<Date> localDateProvider = new AbstractProvider<Date>() {
            @Override
            public Date get() {
                return new Date();
            }
        };

        Converter<String, Date> toStringDate = new AbstractConverter<String, Date>() {
            @Override
            protected Date convert(String source) {
                return DateTimeUtils.parseDate(source, DateTimeUtils.DD_MM_YYYY);
            }
        };

        modelmapper.createTypeMap(String.class, Date.class);
        modelmapper.addConverter(toStringDate);
        modelmapper.getTypeMap(String.class, Date.class).setProvider(localDateProvider);
        return modelmapper;
    }
}
