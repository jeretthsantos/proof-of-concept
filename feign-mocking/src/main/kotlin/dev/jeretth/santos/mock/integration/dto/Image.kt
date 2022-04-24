package dev.jeretth.santos.mock.integration.dto

data class Image(
    val url: String,
    val artist: String,
    val artistUrl: String?,
    val sourceUrl: String?,
    val error: String
)