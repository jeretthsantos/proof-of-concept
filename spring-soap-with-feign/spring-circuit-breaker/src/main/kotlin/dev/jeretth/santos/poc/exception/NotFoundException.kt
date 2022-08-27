package dev.jeretth.santos.poc.exception

class NotFoundException(
    override val message: String?
) : RuntimeException()