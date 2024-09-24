package com.kotlin.kotlincrudapp.config

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JettyConfig {

    @Bean
    fun jettyServletWebServerFactory(): JettyServletWebServerFactory {
        return JettyServletWebServerFactory().apply {
            port = 8081
        }
    }
}