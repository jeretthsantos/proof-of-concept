package dev.jeretth.santos.poc.config

import dev.jeretth.santos.poc.config.flow.ApplicationEventIntegrationFlow
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.integration.config.EnableIntegration

@Configuration
@EnableIntegration
@Import(
    value = [
        ApplicationEventIntegrationFlow::class
    ]
)
class IntegrationFlowConfiguration {

}