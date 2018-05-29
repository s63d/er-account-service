package com.s63d.eraccountservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "PUT", "POST", "OPTIONS", "HEAD")
                .allowedOrigins("*")
                .allowedHeaders("*")
    }

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder()
}