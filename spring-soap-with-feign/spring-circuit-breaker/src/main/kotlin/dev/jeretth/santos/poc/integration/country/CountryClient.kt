package dev.jeretth.santos.poc.integration.country

import dev.jeretth.santos.poc.integration.country.config.CountryClientConfiguration
import dev.jeretth.santos.poc.integration.country.config.CountryClientFallbackFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import santos.jeretth.poc.soap.GetCountryRequest
import santos.jeretth.poc.soap.GetCountryResponse

@FeignClient(
    name = "\${services.country.name}",
    url = "\${services.country.url}",
    configuration = [CountryClientConfiguration::class],
    fallbackFactory = CountryClientFallbackFactory::class
)
interface CountryClient {

    @PostMapping(
        consumes = [MediaType.TEXT_XML_VALUE],
        produces = [MediaType.TEXT_XML_VALUE]
    )
    fun getCountry(@RequestBody request: GetCountryRequest): GetCountryResponse

}