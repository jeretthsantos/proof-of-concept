package dev.jeretth.santos.poc.integration.country.config

import dev.jeretth.santos.poc.exception.ServiceUnavailableException
import dev.jeretth.santos.poc.integration.country.CountryClient
import santos.jeretth.poc.soap.GetCountryRequest
import santos.jeretth.poc.soap.GetCountryResponse
import java.util.concurrent.TimeoutException

class CountryClientFallback(
    private val cause: Throwable?
) : CountryClient {

    override fun getCountry(request: GetCountryRequest): GetCountryResponse {
        if (cause is TimeoutException) {
            throw ServiceUnavailableException("Cannot connect to server")
        }

        throw cause!!
    }

}