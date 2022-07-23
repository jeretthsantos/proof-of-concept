package dev.jeretth.santos.poc.integration.event

import dev.jeretth.santos.poc.data.dto.PublishEventRequest
import org.springframework.integration.annotation.Gateway
import org.springframework.integration.annotation.MessagingGateway

@MessagingGateway
interface EventPublisherGateway {

    @Gateway(requestChannel = "applicationEventChannel")
    fun publishEvent(event: PublishEventRequest)

}