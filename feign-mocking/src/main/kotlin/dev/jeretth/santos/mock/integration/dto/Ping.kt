package dev.jeretth.santos.mock.integration.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Ping(
    @JsonProperty("catboy_says")
    val says: String,

    @JsonProperty("error")
    val error: String,

    @JsonProperty("code")
    val code: Int
)