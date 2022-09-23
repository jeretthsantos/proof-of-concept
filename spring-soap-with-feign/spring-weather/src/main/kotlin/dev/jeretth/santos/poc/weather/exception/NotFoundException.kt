package dev.jeretth.santos.poc.weather.exception

class NotFoundException(
    override val message: String?
) : RuntimeException()