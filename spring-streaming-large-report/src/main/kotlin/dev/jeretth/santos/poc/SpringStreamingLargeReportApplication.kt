package dev.jeretth.santos.poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringStreamingLargeFilesApplication

fun main(args: Array<String>) {
    runApplication<SpringStreamingLargeFilesApplication>(*args)
}
