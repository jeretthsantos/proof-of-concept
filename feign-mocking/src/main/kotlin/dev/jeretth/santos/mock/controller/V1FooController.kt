package dev.jeretth.santos.mock.controller

import dev.jeretth.santos.mock.integration.CatBoyClient
import dev.jeretth.santos.mock.integration.dto.Ping
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

interface V1FooController {

    fun foo(): Ping

}

@RestController
@RequestMapping("/v1/foos")
internal class DefaultV1FooController(val catBoyClient: CatBoyClient) : V1FooController {

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    override fun foo() = catBoyClient.ping()

}