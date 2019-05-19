package se.aourell.axontest.bookcommands.rest

import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import se.aourell.axontest.bookcommands.commands.CreateBookCommand
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
class Api {
    @Autowired
    private lateinit var commandGateway: CommandGateway

    @GetMapping("/create-book/{name}")
    fun createBook(@PathVariable name: String): BookCreation {
        val f: CompletableFuture<String> = commandGateway.send(CreateBookCommand(UUID.randomUUID().toString(), name))

        return BookCreation(
                String.format("Created commands '%s'", name)
        )
    }
}
