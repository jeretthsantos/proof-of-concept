package dev.jeretth.santos.mock.integration

import dev.jeretth.santos.mock.integration.dto.Image
import dev.jeretth.santos.mock.integration.dto.Ping
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "\${services.cat-boy.name}",
    url = "\${services.cat-boy.url}"
)
interface CatBoyClient {

    @GetMapping("\${services.cat-boy.ping-url}")
    fun ping(): Ping

    @GetMapping("\${services.cat-boy.img-url}")
    fun image(): Image

}