package dev.jeretth.santos.poc.exception

class CountryNotFoundException(
    override val message: String?
) : Exception(message)