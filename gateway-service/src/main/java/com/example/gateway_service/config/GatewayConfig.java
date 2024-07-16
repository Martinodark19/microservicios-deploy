package com.example.gateway_service.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig 
{
     @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("authentication-service", r -> r.path("/auth/**")
                        .uri("lb://authentication-service"))
                .route("usuario-service", r -> r.path("/usuario/**")
                        .uri("lb://usuario-service"))
                .route("carro-service", r -> r.path("/carro/**")
                        .uri("lb://carro-service"))
                .route("moto-service", r -> r.path("/moto/**")
                        .uri("lb://moto-service"))
                .build();
    }
}
