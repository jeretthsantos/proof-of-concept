package dev.jeretth.santos.qoute.config

import dev.jeretth.santos.qoute.integration.quote.QuoteClient
import org.springframework.context.annotation.Configuration
import reactivefeign.spring.config.EnableReactiveFeignClients

@Configuration
@EnableReactiveFeignClients(
    clients = [
        QuoteClient::class
    ],
)
class ReactiveFeignConfiguration