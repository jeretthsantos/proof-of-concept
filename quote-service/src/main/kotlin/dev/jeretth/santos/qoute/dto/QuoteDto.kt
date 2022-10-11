package dev.jeretth.santos.qoute.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class QuoteDto(
    @JsonProperty("_id") val id: String,
    @JsonProperty("content") val content: String,
    @JsonProperty("author") val author: String
) : Serializable