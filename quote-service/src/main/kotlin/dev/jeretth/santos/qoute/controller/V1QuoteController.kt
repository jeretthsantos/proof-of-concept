package dev.jeretth.santos.qoute.controller

import dev.jeretth.santos.qoute.dto.QuoteDto
import dev.jeretth.santos.qoute.service.V1QuoteService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

interface V1QuoteController {

    fun getQuote(id: String): QuoteDto

    fun getQuotes(ids: List<String>): List<QuoteDto>

    fun getRandom(): QuoteDto

}

@RestController
@RequestMapping("/v1")
class DefaultV1QuoteController(
    val service: V1QuoteService
) : V1QuoteController {

    private val log = LoggerFactory.getLogger(DefaultV1QuoteController::class.java)

    @GetMapping("/quotes/{id}")
    override fun getQuote(@PathVariable("id") id: String): QuoteDto {
        log.info("Get Quote with id {}", id)
        return service.getQuote(id)
    }

    @GetMapping("/quotes")
    override fun getQuotes(@RequestParam("ids") ids: List<String>): List<QuoteDto> {
        log.info("Get Quotes with ids {}", ids)
        return service.getQuotes(ids)
    }


    @GetMapping("/quotes:random")
    override fun getRandom(): QuoteDto {
        log.info("Get random quote")
        return service.getRandom()
    }

}