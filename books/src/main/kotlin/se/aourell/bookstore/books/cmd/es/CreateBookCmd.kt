package se.aourell.bookstore.books.cmd.es

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class CreateBookCmd (@TargetAggregateIdentifier internal val id: UUID, internal val name: String)
