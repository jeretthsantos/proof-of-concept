package dev.jeretth.santos.poc.exception

class ServiceUnavailableException(
    override val message: String?
) : RuntimeException()