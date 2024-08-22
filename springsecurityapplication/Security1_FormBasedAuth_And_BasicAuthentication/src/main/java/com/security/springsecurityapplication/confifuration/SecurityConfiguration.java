package com.security.springsecurityapplication.confifuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    //form based authentication
    /*
        this is by default we don't need to make this clas
        and write this for form based authentication.
        this is we implemented when we add spring dependency
     */
/*   @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.formLogin(withDefaults());
        return http.build();
   }
 */



    //Basic authentication
    /*
        for that we make this configuration class
        and make changes in defaultSecurityFilterChain method
     */
    /*
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());;
        http.httpBasic(withDefaults());
        return http.build();
    }

    */





    /*
    Both in form based authentication and basic authentication
    there is use or authentication header and JsonId (jsonid for  session management)

    In form based authentication there is payload tab
    but in basic authentication there is no payload tab
     */

    //how to make session management disable/stateless
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(withDefaults());
        return http.build();
    }
}
