package dev.jeretth.santos.poc.service

import dev.jeretth.santos.poc.integration.country.CountryClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import santos.jeretth.poc.soap.GetCountryRequest
import santos.jeretth.poc.soap.GetCountryResponse

interface CountryService {

    fun getCountry(name: String, delay: Long?): GetCountryResponse?

}

@Service
internal class DefaultCountryService(
    private val countryClient: CountryClient
) : CountryService {

    private val log = LoggerFactory.getLogger(DefaultCountryService::class.java)

    override fun getCountry(name: String, delay: Long?): GetCountryResponse? {
        val request = GetCountryRequest()
        request.name = name
        request.delay = delay ?: 0

        return countryClient.getCountry(request)
    }

}