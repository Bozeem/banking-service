package com.bozeemcoder.transactionservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Disable static resource handler for /transaction/** paths
        registry.addResourceHandler("/transaction/**").setCachePeriod(0);
    }
}
