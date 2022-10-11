package dev.jeretth.santos.qoute.integration.quote

import org.springframework.context.annotation.Bean
import reactivefeign.webclient.WebClientFeignCustomizer

class QuoteClientConfiguration {

    @Bean
    fun webClientFeignCustomizer(): WebClientFeignCustomizer {
        return WebClientFeignCustomizer {
            it.defaultHeaders { h ->
                h.set("hello", "Hello Testing")
                h.setBasicAuth("username", "password")
            }
        }
    }

}