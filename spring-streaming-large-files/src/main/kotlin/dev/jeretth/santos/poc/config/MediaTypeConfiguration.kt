package dev.jeretth.santos.poc.config

import org.apache.tika.Tika
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MediaTypeConfiguration {

    @Bean
    fun tika() = Tika()

}