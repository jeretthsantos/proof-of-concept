package dev.jeretth.santos.poc.service

import dev.jeretth.santos.poc.data.dto.PublishEventRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

interface EventSubscriberService {

    fun firstSubscriber(payload: PublishEventRequest)

    fun secondSubscriber(payload: PublishEventRequest)

}

@Service
internal class DefaultEventSubscriberService : EventSubscriberService {

    private val log = LoggerFactory.getLogger(DefaultEventSubscriberService::class.java)

    override fun firstSubscriber(payload: PublishEventRequest) {
        log.info("Receive payload to first subscriber: $payload")
    }

    override fun secondSubscriber(payload: PublishEventRequest) {
        log.info("Receive payload to second subscriber: $payload")
    }

}