package dev.jeretth.santos.poc.controller

import dev.jeretth.santos.poc.data.dto.PublishEventRequest
import dev.jeretth.santos.poc.service.EventPublisherService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

interface EventPublisherController {

    fun publishEvent(event: PublishEventRequest)

}

@RestController
internal class DefaultEventPublisherController(
    private val publisherService: EventPublisherService
) : EventPublisherController {

    @PostMapping("/events")
    override fun publishEvent(@RequestBody event: PublishEventRequest) = publisherService.publishEvent(event)

}