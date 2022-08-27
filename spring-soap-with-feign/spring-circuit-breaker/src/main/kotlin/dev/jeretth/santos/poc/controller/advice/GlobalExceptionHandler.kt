package dev.jeretth.santos.poc.controller.advice

import dev.jeretth.santos.poc.data.dto.BaseResponse
import dev.jeretth.santos.poc.exception.InternalServerErrorException
import dev.jeretth.santos.poc.exception.NotFoundException
import dev.jeretth.santos.poc.exception.ServiceUnavailableException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ServiceUnavailableException::class)
    fun handleServiceUnavailableException(e: ServiceUnavailableException) =
        ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(BaseResponse(message = "Service is unavailable at the moment"))

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException) =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(BaseResponse(message = e.message!!))

    @ExceptionHandler(InternalServerErrorException::class)
    fun handleInternalServerErrorException(e: InternalServerErrorException) =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(BaseResponse(message = e.message!!))

}