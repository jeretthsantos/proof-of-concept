package dev.jeretth.santos.qoute.controller

import dev.jeretth.santos.qoute.dto.ErrorDto
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(FeignException::class)
    fun handleFeignException(e: FeignException): ResponseEntity<ErrorDto> {
        val status = HttpStatus.valueOf(e.status())
        val message = when (status) {
            HttpStatus.NOT_FOUND -> "Quote not found"
            else -> "Failed to get quote"
        }

        return ResponseEntity.status(status).body(ErrorDto(message))
    }

}