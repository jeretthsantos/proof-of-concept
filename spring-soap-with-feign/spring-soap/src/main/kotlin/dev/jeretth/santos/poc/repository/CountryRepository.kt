package dev.jeretth.santos.poc.repository

import org.springframework.stereotype.Repository
import santos.jeretth.poc.soap.Country
import santos.jeretth.poc.soap.Currency
import javax.annotation.PostConstruct

@Repository
class CountryRepository(
    private var countries: ArrayList<Country> = ArrayList()
) {

    @PostConstruct
    fun postConstruct() {
        var country = Country()
        country.name = "Philippines"
        country.population = 3_000_000
        country.capital = "Manila"
        country.currency = Currency.PHP
        countries.add(country)

        country = Country()
        country.name = "Spain"
        country.population = 46_704_314
        country.capital = "Madrid"
        country.currency = Currency.EUR
        countries.add(country)

        country = Country()
        country.name = "Poland"
        country.population = 38_186_860
        country.capital = "Warsaw"
        country.currency = Currency.PLN
        countries.add(country)

        country = Country()
        country.name = "United Kingdom"
        country.population = 63_705_000
        country.capital = "London"
        country.currency = Currency.GBP
        countries.add(country)
    }

    fun findCountry(name: String) = countries.find { it.name.equals(name, true) }

}