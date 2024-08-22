package com.security1.SpringSecurityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import com.security1.SpringSecurityDemo.jwt_security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationFilter filter;


    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user = User.withUsername("user")
                .password("{noop}pass1")
                .build();

        return new InMemoryUserDetailsManager(user);
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/login").permitAll()
                .anyRequest().authenticated());
        http.csrf(csrf -> csrf.disable());

        http.formLogin(formLogin ->  formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/generate-token",true)
                .permitAll());
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return  new JwtAuthenticationFilter();
    }



}
