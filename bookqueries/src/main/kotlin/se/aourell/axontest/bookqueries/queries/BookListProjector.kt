package se.aourell.axontest.bookqueries.queries

import com.example.axontest.book.events.BookCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service

@Service
class BookListProjector {
    val books = mutableListOf<Book>()

    @EventHandler
    fun on(event: BookCreatedEvent) {
        val book = Book(event.id, event.name)
        books.add(book)
    }
}
