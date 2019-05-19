package se.aourell.axontest.bookcommands.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateBookCommand (@TargetAggregateIdentifier internal val id: String, internal val name: String)
