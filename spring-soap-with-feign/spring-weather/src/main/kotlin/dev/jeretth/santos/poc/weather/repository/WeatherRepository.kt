package dev.jeretth.santos.poc.weather.repository

import dev.jeretth.santos.poc.weather.data.model.Weather
import org.springframework.stereotype.Repository
import java.util.*

interface WeatherRepository {

    fun findWeather(id: Long): Optional<Weather>

}

@Repository
internal class DefaultWeatherRepository : WeatherRepository {

    private val weathers = arrayListOf(
        Weather(1, "Rainy"),
        Weather(2, "Sunny"),
        Weather(3, "Cloudy"),
        Weather(4, "Stormy")
    )

    override fun findWeather(id: Long): Optional<Weather> {
        return weathers.stream()
            .filter { it.id == id }
            .findFirst()
    }

}