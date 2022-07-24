package dev.jeretth.santos.poc.controller

import dev.jeretth.santos.poc.exception.CountryNotFoundException
import dev.jeretth.santos.poc.repository.CountryRepository
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

    @PayloadRoot(namespace = "http://jeretth.santos/poc/soap", localPart = "GetCountryRequest")
    @ResponsePayload
    fun getCountry(@RequestPayload request: GetCountryRequest): GetCountryResponse {
        val country = repository.findCountry(request.name)

        if (Objects.isNull(country)) {
            throw CountryNotFoundException("Country ${request.name} not found")
        }

        val response = GetCountryResponse()
        response.country = country

        return response
    }

}
