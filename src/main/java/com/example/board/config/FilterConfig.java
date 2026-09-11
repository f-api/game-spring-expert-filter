package com.example.board.config;

import com.example.board.filter.RequestLoggingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RequestLoggingFilter());
        registration.addUrlPatterns("/posts", "/posts/*");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter(
            @Value("${app.cors.allowed-origin}") String allowedOrigin
    ) {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(List.of(allowedOrigin));
        cors.setAllowedMethods(List.of("GET", "POST"));
        cors.setAllowedHeaders(List.of("Content-Type"));
        cors.setMaxAge(0L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/posts", cors);
        source.registerCorsConfiguration("/posts/**", cors);
        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CorsFilter(source));
        registration.addUrlPatterns("/posts", "/posts/*");
        registration.setOrder(2);
        return registration;
    }
}
