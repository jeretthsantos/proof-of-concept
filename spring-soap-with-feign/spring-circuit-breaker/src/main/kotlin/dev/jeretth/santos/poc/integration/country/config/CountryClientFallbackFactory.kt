package dev.jeretth.santos.poc.integration.country.config

import dev.jeretth.santos.poc.integration.country.CountryClient
import org.springframework.cloud.openfeign.FallbackFactory

class CountryClientFallbackFactory : FallbackFactory<CountryClient> {

    override fun create(cause: Throwable?): CountryClient {
        return CountryClientFallback(cause)
    }

}