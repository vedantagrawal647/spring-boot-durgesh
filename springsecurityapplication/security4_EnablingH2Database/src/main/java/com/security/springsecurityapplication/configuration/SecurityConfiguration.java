package com.security.springsecurityapplication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated());

        http.httpBasic(withDefaults());

        http.headers(headers ->
                headers.frameOptions(frameOptions -> frameOptions.sameOrigin() ));

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    //In memory Authentication
    //Username and passowrd and roles is in memory
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user = User.withUsername("user")
                                .password("{noop}pass1")
                                .roles("USER")
                                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}pass2")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }
}
