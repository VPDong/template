package com.temp.server.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

public abstract class AppConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*"); // 允许任何来源
        corsConfiguration.addAllowedMethod("*"); // 允许任何方法
        corsConfiguration.addAllowedHeader("*"); // 允许任何头部

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            // jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_EMPTY);// 属性为空不序列化
            jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);// 属性为NULL不序列化
            jacksonObjectMapperBuilder.failOnUnknownProperties(false);
        };
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
