package dev.jeretth.santos.qoute

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuoteServiceApplication

fun main(args: Array<String>) {
	runApplication<QuoteServiceApplication>(*args)
}
