package com.example.gateway_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity

public class SecurityConfig 
{
        private JwtFilter jwtFilter;

        public SecurityConfig(JwtFilter jwtFilter) 
        {
           this.jwtFilter = jwtFilter;
        }         

     @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) 
    {
        return http   
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .pathMatchers(HttpMethod.POST, "/auth/testingJWT").permitAll()
                        .pathMatchers("/usuario/**").hasAnyRole("CREATE", "DELETE", "READ", "ADMIN", "UPDATE")
                        .anyExchange().authenticated()
                )
                .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)

                .build();
    }
}


