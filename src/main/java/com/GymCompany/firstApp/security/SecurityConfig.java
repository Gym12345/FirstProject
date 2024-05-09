package com.GymCompany.firstApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/","/registerMenu","/rddCheck","/registerCheck").permitAll()
                        .anyRequest().authenticated() 
        )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/loginMenu") // Custom login page
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .permitAll()
                ).csrf().disable()
                ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
