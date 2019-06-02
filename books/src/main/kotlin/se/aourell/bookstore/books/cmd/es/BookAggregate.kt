package se.aourell.bookstore.books.cmd.es

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.context.annotation.Profile
import java.util.*

@Aggregate
@Profile("cmd-docker")
class BookAggregate {

    @AggregateIdentifier
    private lateinit var id: UUID
    private lateinit var name: String

    protected constructor()

    @CommandHandler
    constructor(cmd: CreateBookCmd) {
        System.out.println("[BookAggregate] Command: CreateBookCmd ${cmd.name} ${cmd.name}")
        val event = BookCreatedEvent(cmd.id, cmd.name)
        AggregateLifecycle.apply(event)
    }

    @EventSourcingHandler
    fun on(evt: BookCreatedEvent) {
        System.out.println("[BookAggregate] Event: BookCreatedEvent ${evt.id} ${evt.name}")
        id = evt.id
        name = evt.name
    }
}
