package se.aourell.bookstore.books.cmd.rest

import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import se.aourell.bookstore.books.cmd.es.CreateBookCmd
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
@Profile("cmd-docker")
class Api {
    @Autowired
    private lateinit var commandGateway: CommandGateway

    @PostMapping("/books")
    fun createBook(@RequestParam name: String): BookCreation {
        val f: CompletableFuture<String> = commandGateway.send(CreateBookCmd(UUID.randomUUID(), name))

        return BookCreation(
                String.format("Created with name '%s'", name)
        )
    }
}
