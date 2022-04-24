package dev.jeretth.santos.mock.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients("dev.jeretth.santos.mock.integration")
@Configuration
class FeignConfiguration {

}