package dev.jeretth.santos.poc.config.flow

import dev.jeretth.santos.poc.data.dto.PublishEventRequest
import dev.jeretth.santos.poc.service.EventSubscriberService
import org.springframework.context.annotation.Bean
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.MessageChannels
import org.springframework.messaging.SubscribableChannel

class ApplicationEventIntegrationFlow {

    /**
     * Multiple channel integration using bridge
     * direct --> pubsub -> subscribers
     */
    @Bean
    fun applicationEvent(service: EventSubscriberService): IntegrationFlow =
        IntegrationFlows.from(MessageChannels.direct("eventChannel"))
            .publishSubscribeChannel { chan ->
                chan.subscribe {
                    it.handle { payload: PublishEventRequest -> service.firstSubscriber(payload) }
                }.subscribe {
                    it.handle { payload: PublishEventRequest -> service.secondSubscriber(payload) }
                }
            }
            .get()

    /**
     * Creates a pubsub channel with name applicationEventChannel
     */
    @Bean
    fun applicationEventChannel(): SubscribableChannel =
        MessageChannels.publishSubscribe("applicationEventChannel").get()

    /**
     * The first subscriber of applicationEventChannel.
     *
     * You can use the annotation @see org.springframework.integration.annotation.ServiceActivator for the
     * handler but it's easier for developers to find who uses the method by defining the flow
     * ourselves.
     */
    @Bean
    fun firstSubscriber(service: EventSubscriberService) =
        IntegrationFlows.from("applicationEventChannel")
            .handle { payload: PublishEventRequest -> service.firstSubscriber(payload) }
            .get()

    /**
     * The second subscriber of applicationEventChannel
     *
     * You can use the annotation @see org.springframework.integration.annotation.ServiceActivator for the
     * handler but it's easier for developers to find who uses the method by defining the flow
     * ourselves.
     */
    @Bean
    fun secondSubscriber(service: EventSubscriberService) =
        IntegrationFlows.from("applicationEventChannel")
            .handle { payload: PublishEventRequest -> service.secondSubscriber(payload) }
            .get()

}