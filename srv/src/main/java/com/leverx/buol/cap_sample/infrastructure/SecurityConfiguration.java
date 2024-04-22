package com.leverx.buol.cap_sample.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(1) // needs to have higher priority than CAP security config
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain appFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(AntPathRequestMatcher.antMatcher("/public/**"))
                .csrf(AbstractHttpConfigurer::disable) // don't insist on csrf tokens in put, post etc.
                .authorizeHttpRequests(r -> r.anyRequest().permitAll())

//                .securityMatcher(AntPathRequestMatcher.antMatcher("/actuator/**"))
//                .httpBasic(Customizer.withDefaults())
//                .authenticationProvider()
//                .authorizeHttpRequests(r -> r.anyRequest().authenticated())

                .build();
    }

}
