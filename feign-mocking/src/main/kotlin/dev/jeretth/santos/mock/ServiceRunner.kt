package dev.jeretth.santos.mock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FeignMockingApplication

fun main(args: Array<String>) {
	runApplication<FeignMockingApplication>(*args)
}
