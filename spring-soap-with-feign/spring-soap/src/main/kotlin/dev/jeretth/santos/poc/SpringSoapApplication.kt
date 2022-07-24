package dev.jeretth.santos.poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringSoapApplication

fun main(args: Array<String>) {
    runApplication<SpringSoapApplication>(*args)
}
