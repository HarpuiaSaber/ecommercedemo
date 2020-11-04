package com.toan.ecommercedemo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        try {
            /// insert to db;
            String sql = "INSERT INTO user(id, dob, enabled, gender, name, password, phone, role, username, email) VALUES(1, '1998-04-25 00:00:00', 1, 1, 'Admin', '$2a$12$utPye5L4xWAUx1IhfY2rAudifM56z5UoAMnZBA1htS5vyl.Lv5BQ.','0123456789', 0, 'admin', 'admin@gmail.com')";
            dataSource.getConnection().prepareStatement(sql).executeUpdate();
        } catch (Exception ex) {
            System.out.println("Data already exists");
        }
        return tokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/seller/**").hasAnyAuthority("SELLER", "ADMIN")
                .antMatchers("/customer/**").hasAnyAuthority("CUSTOMER", "ADMIN")
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/auth").defaultSuccessUrl("/home-direction").failureUrl("/login?e=1")
                .and()
                .rememberMe().rememberMeCookieName("app-remember-me")
                .tokenValiditySeconds(2592000).tokenRepository(persistentTokenRepository())
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/home")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).clearAuthentication(true)
                .invalidateHttpSession(true).deleteCookies("JSESSIONID", "app-remember-me").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-deny")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS).sessionFixation().migrateSession()
                .maximumSessions(-1).sessionRegistry(sessionRegistry());
    }

}
