package se.aourell.books.cmd.rest

import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import se.aourell.books.cmd.es.CreateBookCmd
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
@Profile("cmd-docker")
class Api {
    @Autowired
    private lateinit var commandGateway: CommandGateway

    @PostMapping("/books")
    fun createBook(@RequestBody name: String): Mono<BookCreation> {
        val f: CompletableFuture<String> = commandGateway.send(CreateBookCmd(UUID.randomUUID(), name))

        return Mono.just(BookCreation(
                String.format("Created es '%s'", name)
        ))
    }
}
