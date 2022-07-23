package dev.jeretth.santos.poc.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PublishEventRequest(
    @JsonProperty("name") val name: String,
    @JsonProperty("description") val description: String
)
