package se.aourell.books.query.es

import org.axonframework.eventhandling.EventHandler
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import se.aourell.books.cmd.es.BookCreatedEvent
import java.util.*

@Service
@Profile("query-docker")
class BookListProjector {
    private val books = mutableMapOf<UUID, Book>()

    @EventHandler
    fun on(evt: BookCreatedEvent) {
        System.out.println("[BookListProjector] Event: BookCreatedEvent ${evt.id} ${evt.name}")
        val book = Book(evt.id, evt.name)
        books[evt.id] = book
    }

    fun getAll(): Collection<Book> =
            books.values

    fun getById(id: UUID): Book? =
            books[id]
}
