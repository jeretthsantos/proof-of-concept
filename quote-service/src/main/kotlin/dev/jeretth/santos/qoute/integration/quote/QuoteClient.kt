package dev.jeretth.santos.qoute.integration.quote

import dev.jeretth.santos.qoute.dto.QuoteDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@ReactiveFeignClient(
    configuration = [
        QuoteClientConfiguration::class
    ],
    name = "quote-client",
//    url = "https://api.quotable.io"
    url = "http://pc.me:3000"
)
interface QuoteClient {

    @GetMapping("/quotes/{id}")
    fun getQuote(@PathVariable("id") id: String): Mono<QuoteDto>

    @GetMapping("/random")
    fun getRandom(): Mono<QuoteDto>

}