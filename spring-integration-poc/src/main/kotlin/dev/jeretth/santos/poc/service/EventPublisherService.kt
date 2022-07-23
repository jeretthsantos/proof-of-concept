package dev.jeretth.santos.poc.service

import dev.jeretth.santos.poc.data.dto.PublishEventRequest
import dev.jeretth.santos.poc.integration.event.EventPublisherGateway
import org.springframework.stereotype.Service

interface EventPublisherService {

    fun publishEvent(event: PublishEventRequest)

}

@Service
internal class DefaultEventPublisherService(
    private val gateway: EventPublisherGateway
) : EventPublisherService {

    override fun publishEvent(event: PublishEventRequest) = gateway.publishEvent(event)

}