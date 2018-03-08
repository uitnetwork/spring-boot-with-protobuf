package com.uitnetwork.demo.config

import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter

@Configuration
class ProtobufConfig {
    private val logger = KotlinLogging.logger { }

    @Bean
    fun protobufHttpMessageConverter(): ProtobufHttpMessageConverter {
        logger.info { "Creating ProtobufHttpMessageConverter" }

        return ProtobufHttpMessageConverter()
    }
}
