package dev.jeretth.santos.poc.controller

import dev.jeretth.santos.poc.exception.CountryNotFoundException
import dev.jeretth.santos.poc.repository.CountryRepository
import org.slf4j.LoggerFactory
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload
import santos.jeretth.poc.soap.GetCountryRequest
import santos.jeretth.poc.soap.GetCountryResponse
import java.util.*

@Endpoint
class CountryEndpoint(
    val repository: CountryRepository
) {

    private val log = LoggerFactory.getLogger(CountryEndpoint::class.java)

    @PayloadRoot(namespace = "http://jeretth.santos/poc/soap", localPart = "GetCountryRequest")
    @ResponsePayload
    fun getCountry(@RequestPayload request: GetCountryRequest): GetCountryResponse {
        log.info("Receive request")
        val country = repository.findCountry(request.name)

        if (Objects.isNull(country)) {
            throw CountryNotFoundException("Country ${request.name} not found")
        }

        val response = GetCountryResponse()
        response.country = country

        Thread.sleep(request.delay)
        log.info("Sending response")
        return response
    }

}
