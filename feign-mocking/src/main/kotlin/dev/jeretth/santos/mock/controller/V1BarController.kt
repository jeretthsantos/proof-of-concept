package dev.jeretth.santos.mock.controller

import dev.jeretth.santos.mock.integration.CatBoyClient
import dev.jeretth.santos.mock.integration.dto.Image
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

interface V1BarController {

    fun bar(): Image

}

@RestController
@RequestMapping("/v1/bars")
internal class DefaultV1BarController(val catBoyClient: CatBoyClient) : V1BarController {

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    override fun bar() = catBoyClient.image()

}