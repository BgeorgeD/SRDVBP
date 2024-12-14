package com.example.vacationrecommender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/h2-console/**"),
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/logout"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/js/**"),
                                new AntPathRequestMatcher("/images/**"), // Dacă ai imagini
                                new AntPathRequestMatcher("/videos/**") // Dacă ai video-uri
                        ).permitAll() // Permitem acces fără autentificare la aceste rute
                        .anyRequest().authenticated() // Restul cererilor necesită autentificare
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Permitem iframe-uri pentru H2 Console
                )
                .formLogin(form -> form
                        .loginPage("/login") // Endpoint pentru login
                        .defaultSuccessUrl("/home", true) // Redirectare către `/home` după login
                        .permitAll() // Permitem acces la login fără autentificare
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Endpoint pentru logout
                        .logoutSuccessUrl("/login") // Redirectare către `/login` după logout
                        .permitAll() // Permitem acces la logout fără autentificare
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")) // Dezactivăm CSRF pentru H2 Console
                );

        return http.build();
    }
}
