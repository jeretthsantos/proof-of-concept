package dev.jeretth.santos.poc.controller

import dev.jeretth.santos.poc.service.CountryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import santos.jeretth.poc.soap.GetCountryResponse

interface CountryController {

    fun getCountry(name: String, delay: Long?): GetCountryResponse?

}

@RestController
internal class DefaultCountryController(
    private val service: CountryService
) : CountryController {

    @GetMapping("/countries/{name}")
    override fun getCountry(
        @PathVariable("name") name: String,
        @RequestParam(value = "delay", required = false) delay: Long?
    ) = service.getCountry(name, delay)

}