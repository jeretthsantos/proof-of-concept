package dev.jeretth.santos.poc.exception

class InternalServerErrorException(
    override val message: String?
) : RuntimeException()