package dev.jeretth.santos.poc.weather.service

import dev.jeretth.santos.poc.weather.data.dto.GetWeatherResponse
import dev.jeretth.santos.poc.weather.exception.NotFoundException
import dev.jeretth.santos.poc.weather.repository.WeatherRepository
import org.springframework.stereotype.Service

interface WeatherService {

    fun getWeather(id: Long): GetWeatherResponse

}

@Service
internal class DefaultWeatherService(
    private val repository: WeatherRepository
) : WeatherService {

    override fun getWeather(id: Long): GetWeatherResponse {
        repository.findWeather(id)
            .orElseThrow {
                NotFoundException("Weather not found")
            }
    }

}