package se.aourell.axontest.bookcommands.commands

import com.example.axontest.book.events.BookCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class BookAggregate {

    @AggregateIdentifier
    private lateinit var id: String
    private lateinit var name: String

    protected constructor()

    @CommandHandler
    constructor(cmd: CreateBookCommand) {
        val event = BookCreatedEvent(cmd.id, cmd.name)
        AggregateLifecycle.apply(event)
    }

    @EventSourcingHandler
    fun on(event: BookCreatedEvent) {
        id = event.id
        name = event.name
    }
}