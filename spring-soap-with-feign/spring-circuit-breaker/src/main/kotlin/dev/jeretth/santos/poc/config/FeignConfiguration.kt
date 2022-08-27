package dev.jeretth.santos.poc.config

import feign.Feign
import org.springframework.cloud.openfeign.CircuitBreakerNameResolver
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients("dev.jeretth.santos.poc.integration")
class FeignConfiguration {

    @Bean
    fun circuitBreakerNameResolver(): CircuitBreakerNameResolver {
        return CircuitBreakerNameResolver { _, target, method ->
            Feign.configKey(target.type(), method).replace(Regex("[^a-zA-Z0-9]"), "_")
        }
    }

}