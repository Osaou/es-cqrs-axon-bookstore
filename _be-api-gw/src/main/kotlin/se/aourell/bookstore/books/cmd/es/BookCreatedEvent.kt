package se.aourell.bookstore.books.cmd.es

import java.util.*

data class BookCreatedEvent (val id: UUID, val name: String)
