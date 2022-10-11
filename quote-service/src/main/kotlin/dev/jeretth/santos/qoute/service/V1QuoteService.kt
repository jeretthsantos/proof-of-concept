package dev.jeretth.santos.qoute.service

import com.hazelcast.map.IMap
import dev.jeretth.santos.qoute.dto.QuoteDto
import dev.jeretth.santos.qoute.integration.quote.QuoteClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

interface V1QuoteService {

    fun getQuote(id: String): QuoteDto

    fun getQuotes(ids: List<String>): List<QuoteDto>

    fun getRandom(): QuoteDto

}

@Service
class DefaultV1QuoteService(
    val client: QuoteClient,
    val quotesCache: IMap<String, QuoteDto>
) : V1QuoteService {

    private val log = LoggerFactory.getLogger(DefaultV1QuoteService::class.java)

    //TODO:
    // List of upgrades
    //  * Move duration to circuit breaker
    override fun getQuote(id: String): QuoteDto {
        return getMonoQuote(id).block(Duration.ofSeconds(5))!!
    }

    override fun getQuotes(ids: List<String>): List<QuoteDto> {
        return Flux.fromStream(ids.stream())
            .flatMap(this::getMonoQuote)
            .collectList()
            .block()!!
    }

    //TODO:
    // List of upgrades
    //  * Move duration to circuit breaker
    override fun getRandom(): QuoteDto = client.getRandom().block(Duration.ofSeconds(5))!!

    private fun getMonoQuote(id: String) = Mono
        .fromCompletionStage {
            quotesCache.getAsync(id)
        }
        .switchIfEmpty(client.getQuote(id))
        .doOnNext {
            quotesCache.putAsync(id, it)
        }
}