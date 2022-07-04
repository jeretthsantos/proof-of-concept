package dev.jeretth.santos.poc.controller

import org.apache.tika.Tika
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.nio.file.Files
import java.nio.file.Path

interface V1FileDownloadController {

    fun downloadLargeFile(fileName: String): ResponseEntity<StreamingResponseBody>

}

@RestController
internal class DefaultV1FileDownloadController(
    private val tika: Tika
) : V1FileDownloadController {

    @GetMapping("/v1/downloads/{fileName}")
    override fun downloadLargeFile(@PathVariable("fileName") fileName: String): ResponseEntity<StreamingResponseBody> {
        val file = Path.of(System.getProperty("user.home"), "Downloads", fileName)

        return ResponseEntity.status(OK)
            .header("Content-Disposition", "attachment;filename=$fileName")
            .header("Content-Length", Files.getAttribute(file, "size").toString())
            .header("Content-Type", tika.detect(file))
            .body(StreamingResponseBody {
                Files.newInputStream(file).copyTo(it)
            })
    }

}