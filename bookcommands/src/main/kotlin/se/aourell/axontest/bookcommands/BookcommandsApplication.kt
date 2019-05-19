package se.aourell.axontest.bookcommands

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookcommandsApplication

fun main(args: Array<String>) {
	runApplication<BookcommandsApplication>(*args)
}
