package com.mosesidowu.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder ) {
        return builder.routes()
                .route("employee-service", r -> r.path("/api/employees/**", "/api/departments/**")
                        .uri("lb://employee-service"))

                .route("auth-service", r -> r.path("/api/auth/**")
                        .uri("lb://authentication-service"))
                .build();
    }
}
