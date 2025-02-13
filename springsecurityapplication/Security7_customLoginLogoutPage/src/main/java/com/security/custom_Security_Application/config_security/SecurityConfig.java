package com.security.custom_Security_Application.config_security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getDetailsService(){
        return new CustomUserDetailService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((request) ->
                                           request.requestMatchers("/signin", "/logout","/ab").permitAll()
                                                   .requestMatchers("/invalid").permitAll()
                                                   .anyRequest().authenticated()
                );

        http.formLogin(formlogin -> formlogin.loginPage("/signin")
                                        .loginProcessingUrl("/dologin")
                                        .defaultSuccessUrl("/about")
                                        .failureUrl("/invalid")
        );

        http.logout(logout ->
                logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/"));
        http.csrf(csrf -> csrf.disable());

        return http.build();
     }


}
